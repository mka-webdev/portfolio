package com.uni.app.Application.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "favourite_tracks")
public class FavouriteTrack {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Audiotrack track;

    public FavouriteTrack(User user, Audiotrack track) {
        this.user = user;
        this.track = track;
    }
}
