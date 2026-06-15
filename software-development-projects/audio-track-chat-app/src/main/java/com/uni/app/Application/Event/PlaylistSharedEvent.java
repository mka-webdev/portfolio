package com.uni.app.Application.Event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Domain event representing a playlist being shared.
 * This decouples the audio domain from the chat domain.
 */
@Data
@AllArgsConstructor
public class PlaylistSharedEvent {
    private final String fromUser;
    private final String toUser;
    private final Long playlistId;
    private final String playlistName;
    private final String shareMessage;
    private final LocalDateTime timestamp;
    
    public String generateShareUrl() {
        return "/playlists/" + playlistId;
    }
    
    public String generateDisplayMessage() {
        String baseMessage = generateShareUrl() + "|" + playlistName;
        
        // If shareMessage is null (sharing from chat), add default prefix
        if (shareMessage == null) {
            return "Check out my playlist: " + baseMessage;
        }
        
        // If shareMessage is provided (sharing from playlist view), use custom message
        if (!shareMessage.isBlank()) {
            return shareMessage + " - " + baseMessage;
        }
        
        // If shareMessage is empty string (no custom message from playlist view), no prefix
        return baseMessage;
    }
}