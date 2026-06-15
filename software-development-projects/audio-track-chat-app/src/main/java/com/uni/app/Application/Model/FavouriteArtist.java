package com.uni.app.Application.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "favourite_artists", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "artist" }))
public class FavouriteArtist {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String artist;

    public FavouriteArtist(String username, String artist) {
        this.username = username;
        this.artist = artist;
    }
}
