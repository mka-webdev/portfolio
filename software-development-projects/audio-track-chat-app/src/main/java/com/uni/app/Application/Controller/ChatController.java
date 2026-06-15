package com.uni.app.Application.Controller;

import org.springframework.web.bind.annotation.*;
import com.uni.app.Application.Model.Message;
import com.uni.app.Application.Model.User;
import com.uni.app.Application.Model.Playlist;
import com.uni.app.Application.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.servlet.http.HttpSession;

import com.uni.app.Application.Service.AudioService;
import com.uni.app.Application.Service.ChatService;
import com.uni.app.Application.Service.DomainEventPublisher;
import com.uni.app.Application.Event.PlaylistSharedEvent;

@AllArgsConstructor
@Controller
public class ChatController {
    private final UserRepository users;
    private final AudioService audioService;
    private final ChatService chatService;
    private final DomainEventPublisher eventPublisher;

    /*
     *Handles GET requests to "/chat" endpoint.
     *Verifies users existence, retrieves past messages and past chat partners.
     *Passes relevant data to the view
     */
    @GetMapping("/chat")
    public String chat(@RequestParam(defaultValue = "friend") String with, Model model, HttpSession session) {
        // Get current user from session, redirect to login if not found
        String currentUser = (String) session.getAttribute("currentUser");
        
        if (currentUser == null) {
            return "redirect:/login?next=/chat?with=" + with;
        }

        // Might require decoupling later
        // Ensure current user and chat partner both exist
        users.findByUsername(currentUser).orElseGet(() -> users.save(new User(currentUser)));
        users.findByUsername(with).orElseGet(() -> users.save(new User(with)));

        // Find messages between current user and specified user
        List<Message> conversation = chatService.getMessagesWithPerson(currentUser, with);

        // Find previous chat partners
        Set<String> chatPartners = chatService.getChatPartners(currentUser);

        // Add attributes to the model for rendering
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("with", with);
        model.addAttribute("messages", conversation);
        model.addAttribute("chatPartners", chatPartners);

        // DECOUPLED: Get playlists via service layer instead of direct repository access
        List<Playlist> userPlaylists = audioService.getUserPlaylists(currentUser);
        model.addAttribute("availablePlaylists", userPlaylists);

        return "chat";
    }

    /*
     * Handles POST requests for sending new messages
     * Saves new message if the text is not empty
     * Redirects back to the relevant chat with specified user
     */
    @PostMapping("/chat/send")
    public String send(@RequestParam String to, @RequestParam String text, HttpSession session) {
        // Get current user from session
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (text != null && !text.isBlank()) {
            chatService.saveMessage(new Message(currentUser, to, text.trim(), LocalDateTime.now()));
        }
        return "redirect:/chat?with=" + to;
    }

    // Share a playlist (by ID) from chat.
    @PostMapping("/chat/share-playlist")
    public String sharePlaylist(@RequestParam String to, @RequestParam(required = false) String playlistId, HttpSession session) {
        // Get current user from session
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        if (playlistId == null || playlistId.isBlank()) {
            chatService.saveMessage(new Message(currentUser, to, "Please select a playlist.", LocalDateTime.now()));
            return "redirect:/chat?with=" + to;
        }

        final Long id;
        try {
            id = Long.valueOf(playlistId.trim());
        } catch (NumberFormatException ex) {
            chatService.saveMessage(new Message(currentUser, to, "That didn't look like a valid playlist ID.", LocalDateTime.now()));
            return "redirect:/chat?with=" + to;
        }

        // DECOUPLED: Use service layer and event publishing instead of direct repository access
        Playlist playlist = audioService.findPlaylistByIdAndOwner(id, currentUser);
        if (playlist == null) {
            chatService.saveMessage(new Message(currentUser, to, "Sorry, playlist #" + id + " doesn't exist.", LocalDateTime.now()));
            return "redirect:/chat?with=" + to;
        }

        // Share playlist with recipient
        if (playlist.getSharedWithUsers() == null) {
            playlist.setSharedWithUsers(new HashSet<>());
        }   

        playlist.getSharedWithUsers().add(to);
        
        audioService.savePlaylist(playlist);

        // Publish event instead of directly writing to message repository
        PlaylistSharedEvent event = new PlaylistSharedEvent(
            currentUser,
            to,
            playlist.getId(),
            playlist.getName(),
            null, // No custom message from chat sharing
            LocalDateTime.now()
        );
        
        eventPublisher.publishPlaylistShared(event);
        return "redirect:/chat?with=" + to;
    }
}
