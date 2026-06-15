package com.uni.app.Application.Controller;

import com.uni.app.Application.Service.IAudioService;
import com.uni.app.Application.Model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.*;

@RestController
@RequestMapping("/api/audio")
@AllArgsConstructor
public class AudioRestController {
    private final IAudioService audioService;

    // rest endpoint to get all audio tracks
    @GetMapping("/tracks")
    public ResponseEntity<List<Audiotrack>> getTracks() {
        return ResponseEntity.ok(audioService.getAllTracks());
    }

    // rest endpoint to get a specific audio track by ID
    @GetMapping("/tracks/{id}")
    public ResponseEntity<Audiotrack> getTrack(@PathVariable Long id) {
        return ResponseEntity.ok(audioService.getTrackById(id));
    }

    // rest endpoint to get all playlists connected to a user
    @GetMapping("/playlists")
    public ResponseEntity<List<Playlist>> getPlaylists(@RequestParam String username) {
        return ResponseEntity.ok(audioService.getUserPlaylists(username));
    }

    // rest endpoint to find a playlist by ID and owner username
    @GetMapping("/playlists/{id}")
    public ResponseEntity<Playlist> findPlaylist(
            @PathVariable Long id,
            @RequestParam String ownerUsername) {
        return ResponseEntity.ok(audioService.findPlaylistByIdAndOwner(id, ownerUsername));
    }

    // rest endpoint to create a new playlist
    @PostMapping("/playlists")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String username = request.get("username");
        return ResponseEntity.ok(audioService.createPlaylist(name, username));
    }

    // rest endpoint to add a track to a playlist
    @PostMapping("/playlists/{playlistId}/tracks/{trackId}")
    public ResponseEntity<Void> addTrackToPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long trackId) {
        audioService.addTrackToPlaylist(playlistId, trackId);
        return ResponseEntity.ok().build();
    }

    // rest endpoint to remove a track from a playlist
    @DeleteMapping("/playlists/{playlistId}/tracks/{trackId}")
    public ResponseEntity<Void> removeTrackFromPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long trackId) {
        audioService.removeTrackFromPlaylist(playlistId, trackId);
        return ResponseEntity.ok().build();
    }

    // rest endpoint to rate a track
    @PostMapping("/tracks/{trackId}/rate")
    public ResponseEntity<Void> rateTrack(
            @PathVariable Long trackId,
            @RequestParam String username,
            @RequestParam int rating) {
        audioService.rateTrack(username, trackId, rating);
        return ResponseEntity.ok().build();
    }

    // rest endpoint to toggle favorite status of a track
    @PostMapping("/tracks/{trackId}/favorite")
    public ResponseEntity<Void> toggleFavorite(
            @PathVariable Long trackId,
            @RequestParam String username) {
        audioService.toggleFavorite(username, trackId);
        return ResponseEntity.ok().build();
    }

    // rest endpoint to get all favorite track IDs of a user
    @GetMapping("/users/{username}/favorites")
    public ResponseEntity<Set<Long>> getUserFavorites(
            @PathVariable String username) {
        return ResponseEntity.ok(audioService.getUserFavorites(username));
    }

    // rest endpoint to handle exceptions globally for this controller
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleError(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}