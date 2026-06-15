package com.uni.app.Application.Service;

import com.uni.app.Application.Event.PlaylistSharedEvent;

/**
 * Event publisher interface to decouple domains.
 * Audio domain publishes events, Chat domain subscribes to them.
 */
public interface DomainEventPublisher {
    void publishPlaylistShared(PlaylistSharedEvent event);
}