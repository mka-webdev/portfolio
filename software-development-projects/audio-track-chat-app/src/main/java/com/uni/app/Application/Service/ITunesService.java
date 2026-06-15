package com.uni.app.Application.Service;

import com.uni.app.Application.Model.Audiotrack;
import com.uni.app.Application.Model.ITunesResponse;
import com.uni.app.Application.Model.ITunesTrack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ITunesService {
    private static final Logger logger = LoggerFactory.getLogger(ITunesService.class);
    private final RestTemplate rest;

    public ITunesService(RestTemplate rest) {
        this.rest = rest;
    }

    // Main search method used by the audio controller
    public List<Audiotrack> searchTracks(String term) {
        if (term != null && !term.trim().isEmpty()) {
            String url = UriComponentsBuilder.fromUriString("https://itunes.apple.com/search")
                    .queryParam("term", term.trim())
                    .queryParam("media", "music")
                    .queryParam("limit", 25)
                    .queryParam("country", "AU")
                    .encode()
                    .toUriString();

            logger.debug("Searching iTunes with URL: {}", url);

            try {
                // Response item that contains the result count + results in a list
                ITunesResponse resp = rest.getForObject(url, ITunesResponse.class);

                List<Audiotrack> validTracks = new ArrayList<>();
                if (resp != null && resp.getResultCount() > 0 && resp.getResults() != null){
                    for (ITunesTrack track : resp.getResults()) {
                        if (track.getTrackName() != null && track.getArtistName() != null) {
                            validTracks.add(convertToAudiotrack(track));
                        }
                    }
                    return validTracks;
                }
            } catch (RestClientException e) {
                logger.error("iTunes API error: {}", e.getMessage());
            }
        } 
        return Collections.emptyList();
    }

    // Helper method to convert iTunes track to our Audiotrack model
    private Audiotrack convertToAudiotrack(ITunesTrack track) {
        String artworkUrl = track.getArtworkUrl100();
        if (artworkUrl != null && !artworkUrl.isEmpty()) {
            // Upgrade to 300x300 for better quality
            artworkUrl = artworkUrl.replace("100x100", "300x300");
        }

        return new Audiotrack(
                track.getTrackName(),
                track.getArtistName(),
                track.getPrimaryGenreName(),
                "audio/mpeg",
                track.getTrackTimeMillis() != null ? track.getTrackTimeMillis() / 1000.0 : 0.0,
                0L,
                track.getPreviewUrl(),
                artworkUrl // ✅ ADD THIS
        );
    }
}
