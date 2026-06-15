package com.uni.app.Application.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "playlist_items")
public class PlaylistItem {
    @Id
    @GeneratedValue
    private Long id;
    private Long playlistId;
    private Long trackId;
    private int position;

    public PlaylistItem(Long playlistId, Long trackId, int position) {
        this.playlistId = playlistId;
        this.trackId = trackId;
        this.position = position;
    }
}
