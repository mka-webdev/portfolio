package com.uni.app.Application.Service;

import com.uni.app.Application.Event.PlaylistSharedEvent;
import com.uni.app.Application.Model.Message;
import com.uni.app.Application.Repository.MessageRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;

/**
 * Chat domain service that handles playlist sharing events.
 * This decouples the chat functionality from the audio domain.
 */
@Service
@AllArgsConstructor
public class ChatEventHandler {
    
    private final MessageRepository messages;
    
    /**
     * Handles playlist shared events by creating chat messages.
     * This allows the chat domain to scale independently from audio domain.
     */
    @EventListener
    public void handlePlaylistShared(PlaylistSharedEvent event) {
        String messageBody = event.generateDisplayMessage();
        
        Message chatMessage = new Message(
            event.getFromUser(),
            event.getToUser(), 
            messageBody,
            event.getTimestamp()
        );
        
        messages.save(chatMessage);
    }
}
