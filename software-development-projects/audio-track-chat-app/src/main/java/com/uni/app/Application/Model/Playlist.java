package com.uni.app.Application.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String ownerUsername; // User who created this playlist
    private LocalDateTime createdAt;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> sharedWithUsers = new HashSet<>(); // Users with whom this playlist is shared

    public Playlist(@NonNull String name, @NonNull String ownerUsername, LocalDateTime createdAt) {
        this.name = name;
        this.ownerUsername = ownerUsername;
        this.createdAt = createdAt;
        this.sharedWithUsers = new HashSet<>();
    }
}
