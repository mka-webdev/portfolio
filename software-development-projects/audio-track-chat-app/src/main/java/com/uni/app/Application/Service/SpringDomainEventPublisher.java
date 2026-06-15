package com.uni.app.Application.Service;

import com.uni.app.Application.Event.PlaylistSharedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

/**
 * Spring-based implementation of domain event publisher.
 * Uses Spring's ApplicationEventPublisher for in-memory event handling.
 * Can be replaced with message queues (RabbitMQ, Kafka) for distributed scaling.
 */
@Service
@AllArgsConstructor
public class SpringDomainEventPublisher implements DomainEventPublisher {
    
    private final ApplicationEventPublisher eventPublisher;
    
    @Override
    public void publishPlaylistShared(PlaylistSharedEvent event) {
        eventPublisher.publishEvent(event);
    }
}