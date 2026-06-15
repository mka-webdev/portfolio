package com.uni.app.Application.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "audiotracks")
public class Audiotrack {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private String contentType;
    private double duration;
    private Long fileSize;
    private String filePath;

    @Column(length = 500)
    private String artworkUrl;

    public Audiotrack(String title, String artist, String genre, String contentType, double duration, long fileSize,
            String filePath) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.contentType = contentType;
        this.duration = duration;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    // Constructor with artwork
    public Audiotrack(String title, String artist, String genre, String contentType, double duration, long fileSize,
            String filePath, String artworkUrl) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.contentType = contentType;
        this.duration = duration;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.artworkUrl = artworkUrl;
    }
}
