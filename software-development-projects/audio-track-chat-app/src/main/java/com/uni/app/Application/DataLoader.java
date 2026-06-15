package com.uni.app.Application;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.uni.app.Application.Model.Message;
import com.uni.app.Application.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni.app.Application.Model.Audiotrack;
import com.uni.app.Application.Model.Playlist;
import com.uni.app.Application.Model.PlaylistItem;
import com.uni.app.Application.Repository.MessageRepository;
import com.uni.app.Application.Repository.UserRepository;
import com.uni.app.Application.Repository.AudiotrackRepository;
import com.uni.app.Application.Repository.PlaylistRepository;
import com.uni.app.Application.Repository.PlaylistItemRepository;

import java.io.InputStream;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;

// DataLoader to seed initial data when the application starts
@Component
public class DataLoader implements ApplicationRunner {
    private final UserRepository users;
    private final MessageRepository messages;
    private final AudiotrackRepository audiotracks;
    private final PlaylistRepository playlists;
    private final PlaylistItemRepository items;

    public DataLoader(UserRepository users,
            MessageRepository messages,
            AudiotrackRepository audiotracks,
            PlaylistRepository playlists,
            PlaylistItemRepository items) {
        this.users = users;
        this.messages = messages;
        this.audiotracks = audiotracks;
        this.playlists = playlists;
        this.items = items;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Ensure default users exist
        users.findByUsername("you").orElseGet(() -> users.save(new User("you")));
        users.findByUsername("friend").orElseGet(() -> users.save(new User("friend")));

        // Seed basic initial messages if none exist
        if (messages.count() == 0) {
            messages.save(new Message("you", "friend", "Hello!", LocalDateTime.now().minusMinutes(2)));
            messages.save(new Message("friend", "you", "Hello, how are you?", LocalDateTime.now().minusMinutes(1)));
        }

        // Seed the audio tracks from the JSON metadata file if none exist
        if (audiotracks.count() == 0) {
            try (InputStream is = getClass().getResourceAsStream("/static/music/songMetaData.json")) {
                ObjectMapper mapper = new ObjectMapper();
                List<Map<String, Object>> tracks = mapper.readValue(is, new TypeReference<List<Map<String, Object>>>() {
                });
                for (Map<String, Object> meta : tracks) {
                    String title = (String) meta.get("title");
                    String artist = (String) meta.get("artist");
                    String genre = (String) meta.get("genre");
                    String contentType = (String) meta.get("contentType");
                    double duration = Double.parseDouble(meta.get("duration").toString());
                    String safeArtist = artist != null ? artist.replace("/", "_") : "";
                    String fileName = title + " - " + safeArtist + ".mp3";
                    String filePath = "/music/" + fileName;
                    boolean fileExists = false;
                    long fileSize = 0;

                    // Check if the audio file exists in the static resources
                    try {
                        InputStream fileStream = getClass().getResourceAsStream("/static/music/" + fileName);
                        if (fileStream != null) {
                            fileSize = fileStream.available();
                            fileStream.close();
                            fileExists = true;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    // Only save the track if the corresponding audio file exists
                    if (fileExists) {
                        Audiotrack track = new Audiotrack(title, artist, genre, contentType, duration, fileSize, filePath);
                        audiotracks.save(track);
                    } else {
                        System.out.println("Missing audio file: " + fileName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Create a demo playlist visible to all users when no playlists exist but only when audio tracks are present
        if (playlists.count() == 0 && audiotracks.count() > 0) {
            List<Audiotrack> allTracks = audiotracks.findAll();
            
            // Create demo playlist visible to all users
            Playlist demoPlaylist = new Playlist("🎵 Demo Playlist", "demo", LocalDateTime.now());
            playlists.save(demoPlaylist);
            
            // Add first 3 tracks to demo playlist
            for (int i = 0; i < Math.min(3, allTracks.size()); i++) {
                Audiotrack track = allTracks.get(i);
                PlaylistItem item = new PlaylistItem(demoPlaylist.getId(), track.getId(), i + 1);
                items.save(item);
            }
        }

    }
}
