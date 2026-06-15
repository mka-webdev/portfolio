package com.uni.app.Application;

import com.uni.app.Application.Model.Audiotrack;
import com.uni.app.Application.Service.ITunesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
class ITunesServiceIntegrationTests {

    @Autowired ITunesService itunesService;

    @Autowired RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void searchTracks_returnsValidResults() {
        String fakeApiResponse = """
            {
              "resultCount": 1,
              "results": [
                {
                  "trackName": "Mock Song",
                  "artistName": "Mock Artist",
                  "primaryGenreName": "Pop",
                  "trackTimeMillis": 200000,
                  "previewUrl": "http://example.com/preview.mp3",
                  "artworkUrl100": "http://example.com/art.jpg"
                }
              ]
            }
            """;

        mockServer.expect(requestTo(org.hamcrest.Matchers.containsString("itunes.apple.com/search")))
                  .andRespond(withSuccess(fakeApiResponse, MediaType.APPLICATION_JSON));

        List<Audiotrack> results = itunesService.searchTracks("test");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).isEqualTo("Mock Song");
        assertThat(results.get(0).getArtist()).isEqualTo("Mock Artist");
    }
}
