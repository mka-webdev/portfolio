package com.uni.app.Application.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ratings", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "trackId" }))
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long trackId;

    @Column(nullable = false)
    private int stars;

    public Rating(String username, Long trackId, int stars) {
        this.username = username;
        this.trackId = trackId;
        this.stars = stars;
    }
}
