package com.uni.app.Application.Controller;

import com.uni.app.Application.Model.Audiotrack;

import com.uni.app.Application.Service.AudioService;
import com.uni.app.Application.Service.ITunesService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class AudioController {
    private static final Logger logger = LoggerFactory.getLogger(AudioController.class);
    
    // Services
    private final ITunesService itunesService;   
    private final AudioService audioService;

    // Main audio page with filtering and search
    @GetMapping("/audio")
    public String audio(
            @RequestParam(required = false) String favouriteArtist,
            @RequestParam(required = false) Long favouriteTrack,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false, defaultValue = "false") boolean showFavourites,
            @RequestParam(required = false) String songQuery,
            @RequestParam(required = false) String artistQuery,
            Model model,
            HttpSession session) {

        String username = (String) session.getAttribute("currentUser");
        if (username == null) {
            return "redirect:/login?next=/audio";
        }

        List<Audiotrack> allTracks = audioService.getAllTracks();

        Set<Long> favIds = audioService.getUserFavorites(username);
        Map<Long, Integer> myRatings = audioService.getUserRatings(username);
        Set<String> favouriteArtistNames = audioService.getFavoriteArtistNames(username);

        // Create a list of tracks filtered by artist and/or rating
        List<Audiotrack> filteredTracks = new ArrayList<>();
        if ((favouriteArtist == null || favouriteArtist.isBlank()) && !showFavourites && rating == null) {
            filteredTracks.addAll(allTracks);
        } else {
            for (Audiotrack t : allTracks) {
                if ((favouriteArtist == null || favouriteArtist.isEmpty() || t.getArtist().equals(favouriteArtist)) &&
                        (!showFavourites || favIds.contains(t.getId())) &&
                        (rating == null
                                || (myRatings.containsKey(t.getId()) && rating.equals(myRatings.get(t.getId()))))) {
                    filteredTracks.add(t);
                }
            }
        }

        // Add favourited tracks
        List<Audiotrack> favouriteTracks = new ArrayList<>();
        for (Audiotrack t : allTracks) {
            if (favIds.contains(t.getId())) {
                favouriteTracks.add(t);
            }
        }

        // If search term exists
        if ((songQuery != null && !songQuery.trim().isEmpty())
                || (artistQuery != null && !artistQuery.trim().isEmpty())) {
            try {
                // Add results from ITunes
                List<Audiotrack> searchResults = performItunesSearch(songQuery, artistQuery);
                model.addAttribute("searchResults", searchResults);

                model.addAttribute("songQuery", songQuery);
                model.addAttribute("artistQuery", artistQuery);

            } catch (Exception e) {
                logger.error("Search error: {}", e.getMessage());
                model.addAttribute("searchResults", new ArrayList<>());
                model.addAttribute("searchError", "An error occurred while searching. Please try again.");
            }
        } else {
            model.addAttribute("searchResults", new ArrayList<>());
        }

        model.addAttribute("tracks", filteredTracks);
        model.addAttribute("favouritesByTrackId", favIds);
        model.addAttribute("favouriteTracks", favouriteTracks);
        model.addAttribute("myRatings", myRatings);
        model.addAttribute("favouriteArtistNames", favouriteArtistNames);
        model.addAttribute("currentUser", username);
        model.addAttribute("allTracks", allTracks);

        return "audio";
    }

    // Favourite a track
    @PostMapping("/audio/{id}/favourite")
    public String toggleFavourite(@PathVariable Long id, HttpSession session) {
        String username = (String) session.getAttribute("currentUser");
        if (username == null) {
            return "redirect:/login?next=/audio";
        }

        Audiotrack track = audioService.getTrackById(id);
        if (track == null)
            return "redirect:/audio";

        try {
            audioService.toggleFavorite(username, id);
        } catch (Exception e) {
            logger.error("Error toggling favorite: {}", e.getMessage());
        }

        return "redirect:/audio";
    }

    // Favourite an artist
    @PostMapping("/audio/artist/favourite")
    public String toggleArtistFavourite(@RequestParam String artist, HttpSession session) {
        String username = (String) session.getAttribute("currentUser");
        if (username == null) {
            return "redirect:/login?next=/audio";
        }

        if (artist == null || artist.isBlank()) {
            return "redirect:/audio";
        }

        try {
            audioService.toggleArtistFavorite(username, artist);
        } catch (Exception e) {
            logger.error("Error toggling artist favorite: {}", e.getMessage());
        }

        return "redirect:/audio";
    }

    // Rate a track
    @PostMapping("/audio/{id}/rate")
    public String rateTrack(@PathVariable Long id,
            @RequestParam int stars,
            HttpSession session) {
        String username = (String) session.getAttribute("currentUser");
        if (username == null) {
            return "redirect:/login?next=/audio";
        }

        int clamped = Math.max(0, Math.min(5, stars));

        Audiotrack track = audioService.getTrackById(id);

        if (track == null)
            return "redirect:/audio";

        try {
            audioService.rateTrack(username, id, clamped);
        } catch (Exception e) {
            logger.error("Error rating track: {}", e.getMessage());
        }

        return "redirect:/audio";
    }

    // Add a new track with metadata
    @PostMapping("/audio/addMeta")
    public String addMeta(@RequestParam String title,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false, defaultValue = "") String genre,
            @RequestParam(required = false, defaultValue = "audio/mpeg") String contentType,
            @RequestParam(required = false, defaultValue = "0") double duration,
            @RequestParam(required = false, defaultValue = "0") long fileSize,
            @RequestParam String filePath) {
        if (title == null || title.isBlank() || filePath == null || filePath.isBlank()) {
            return "redirect:/audio";
        }

        Audiotrack t = new Audiotrack(
                title.trim(),
                (artist == null) ? "" : artist.trim(),
                (genre == null) ? "" : genre.trim(),
                (contentType == null || contentType.isBlank()) ? "audio/mpeg" : contentType.trim(),
                Math.max(0.0, duration),
                Math.max(0L, fileSize),
                filePath.trim());
        audioService.saveTrack(t);
        return "redirect:/audio";
    }

    // Add a track from ITunes search results
    @PostMapping("/audio/itunes/add")
    public String addFromItunes(
            @RequestParam String title,
            @RequestParam String artist,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String filePath,
            @RequestParam(required = false) Double duration,
            @RequestParam(required = false) String artworkUrl, // ✅ ADD THIS
            HttpSession session) {

        String username = (String) session.getAttribute("currentUser");
        if (username == null) {
            return "redirect:/login?next=/audio";
        }

        try {
            Audiotrack track = new Audiotrack(
                title,
                artist,
                genre != null ? genre : "Unknown",
                "audio/mpeg",
                duration != null ? duration : 0.0,
                0L,
                filePath != null ? filePath : "",
                artworkUrl != null ? artworkUrl : ""
            );

            audioService.saveTrack(track);
            logger.info("Added track to library: {} by {}", title, artist);

        } catch (Exception e) {
            logger.error("Error adding track to library: {}", e.getMessage());
        }

        return "redirect:/audio";
    }

    private List<Audiotrack> performItunesSearch(String songQuery, String artistQuery) {
        List<Audiotrack> allTracks;
        List<Audiotrack> result = new ArrayList<>();

        // Determine which query to search with
        String searchTerm;
        String songLower = null;
        String artistLower = null;
        if (songQuery != null && !songQuery.trim().isEmpty()) {
            searchTerm = songQuery.trim() + " ";
            songLower = songQuery.trim().toLowerCase();
        } else if (artistQuery != null && !artistQuery.trim().isEmpty()) {
            searchTerm = artistQuery.trim() + " ";
            artistLower = artistQuery.trim().toLowerCase();
        } else {
            // Empty
            return result;
        }

        // Get tracks from ITunes
        allTracks = itunesService.searchTracks(searchTerm.trim());

        // For each track check that the song title or artist contains the input query
        for (Audiotrack track : allTracks) {
            boolean s = true;
            boolean a = true;
            if (songLower != null) {
                s = track.getTitle().toLowerCase().contains(songLower);
            }
            if (artistLower != null) {
                a = track.getArtist().toLowerCase().contains(artistLower);
            }

            if (s && a) {
                result.add(track);
                if (songQuery != null && artistQuery != null && result.size() >= 10)
                    break;
                else if (result.size() >= 25)
                    break;
            }
        }
        return result;
    }
}
