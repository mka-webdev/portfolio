package com.uni.app.Application.Model;

import lombok.Data;

@Data
public class ITunesTrack {
    private String trackName;
    private String artistName;
    private String primaryGenreName;
    private String previewUrl;
    private Integer trackTimeMillis;
    private String artworkUrl60; // 60x60
    private String artworkUrl100; // 100x100
}
