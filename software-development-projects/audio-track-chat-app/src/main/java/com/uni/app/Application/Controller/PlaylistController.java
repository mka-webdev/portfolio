package com.uni.app.Application.Controller;

import com.uni.app.Application.Event.PlaylistSharedEvent;
import com.uni.app.Application.Model.Audiotrack;
import com.uni.app.Application.Model.Playlist;
import com.uni.app.Application.Model.PlaylistItem;
import com.uni.app.Application.Service.AudioService;
import com.uni.app.Application.Service.ChatService;
import com.uni.app.Application.Service.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Controller
public class PlaylistController {

    private final ChatService chatService;
    private final DomainEventPublisher eventPublisher; // DECOUPLED EVENT PUBLISHING
    private final AudioService audioService;



    // List all playlists + create form
    @GetMapping("/playlists")
    public String list(Model model, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login?next=/playlists";
        }
        
        // Find playlists owned by user
        
        // List<Playlist> visiblePlaylists = playlists.findVisiblePlaylists(currentUser);
        List<Playlist> visiblePlaylists = audioService.getUserPlaylists(currentUser);
        // Find playlists shared with current user
        List<Playlist> sharedPlaylists = audioService.getSharedPlaylistsForUser(currentUser);

        // Combine and remove duplicates
        Set<Playlist> allPlaylists = new HashSet<>(visiblePlaylists);
        allPlaylists.addAll(sharedPlaylists);

        // Sort playlists by creation date
        List<Playlist> sortedPlaylists = new ArrayList<>(allPlaylists);
        sortedPlaylists.sort(Comparator.comparing(Playlist::getCreatedAt));

        model.addAttribute("playlists", sortedPlaylists);
        model.addAttribute("currentUser", currentUser);
        return "playlists";
    }

    // Create a playlist
    @PostMapping("/playlists/create")
    public String create(@RequestParam String name, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        if (name != null && !name.isBlank()) {
            // Create playlist with owner information
            audioService.createPlaylist(name.trim(), currentUser);
        }
        return "redirect:/playlists";
    }

    // View a single playlist + items + add-item form
    @GetMapping("/playlists/{id}")
    public String view(@PathVariable Long id, Model model, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login?next=/playlists/" + id;
        }

        if (!audioService.canUserAccessPlaylist(id, currentUser)) {
            return "redirect:/playlists";
        }
        
        Playlist playlist = audioService.findPlaylistById(id);
        List<PlaylistItem> its = audioService.getPlaylistItems(id);

        // Build track lookup
        Set<Long> trackIds = new HashSet<>();
        for (PlaylistItem i : its) {
            trackIds.add(i.getTrackId());
        }
        
        List<Audiotrack> tracks = audioService.getTracksByIds(trackIds);
        Map<Long, Audiotrack> trackMap = new HashMap<>();
        for (Audiotrack t : tracks) {
            trackMap.put(t.getId(), t);
        }

        List<Audiotrack> allTracks = audioService.getAllTracksSorted();

        model.addAttribute("playlist", playlist);
        model.addAttribute("items", its);
        model.addAttribute("trackMap", trackMap);
        model.addAttribute("allTracks", allTracks);
        model.addAttribute("chatPartners", chatService.getChatPartners(currentUser));
        model.addAttribute("currentUser", currentUser);
        return "playlist-view";
    }

    // Add an item to a playlist 
    @PostMapping("/playlists/{id}/add")
    public String addItem(@PathVariable Long id, @RequestParam Long trackId, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        if (!audioService.canUserAccessPlaylist(id, currentUser)) {
            return "redirect:/playlists";
        }
        
        if (!audioService.trackExists(trackId)) {
            return "redirect:/playlists/" + id;
        }
        
        audioService.addTrackToPlaylist(id, trackId);
        return "redirect:/playlists/" + id;
    }

    // Share a playlist by pushing a labeled link into chat
    @PostMapping("/playlists/{id}/share")
    public String share(@PathVariable Long id, @RequestParam String to, 
                    @RequestParam(required = false) String message, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        Playlist playlist = audioService.findPlaylistById(id);
        if (playlist == null || (!playlist.getOwnerUsername().equals(currentUser) && !playlist.getOwnerUsername().equals("demo"))) {
            return "redirect:/playlists";
        }

        audioService.addUserToSharedPlaylist(id, to);

        // Publish event
        PlaylistSharedEvent event = new PlaylistSharedEvent(
            currentUser, to, playlist.getId(), playlist.getName(), message, LocalDateTime.now()
        );
        eventPublisher.publishPlaylistShared(event);
        return "redirect:/playlists/" + id;
    }
}
