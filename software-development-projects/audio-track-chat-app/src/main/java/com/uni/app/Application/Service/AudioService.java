package com.uni.app.Application.Service;

import com.uni.app.Application.Model.*;
import com.uni.app.Application.Repository.*;
import com.uni.app.Application.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Sort;

@Service
@AllArgsConstructor
@Transactional
public class AudioService implements IAudioService {
    private final PlaylistRepository playlistRepository;
    private final AudiotrackRepository audiotrackRepository;
    private final PlaylistItemRepository playlistItemRepository;
    private final FavouriteTrackRepository favoriteRepository;
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final FavouriteArtistRepository favouriteArtistRepository;


    // retrieve all audio tracks from audio repository
    @Override
    public List<Audiotrack> getAllTracks() {
        return audiotrackRepository.findAll();
    }

    // retrieve a specific audio track by its ID
    @Override
    public Audiotrack getTrackById(Long id) {
        return audiotrackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track not found"));
    }

    // retrieve all playlists visible to a specific user
    @Override
    public List<Playlist> getUserPlaylists(String username) {
        List<Playlist> filteredPlaylist = new ArrayList<>();
        // || !username.trim().isEmpty()
        if (username != null) {
            List<Playlist> playlists = playlistRepository.findAll();
            for (Playlist p : playlists) {
                if (username.equals(p.getOwnerUsername())) {
                    filteredPlaylist.add(p);
                }
            }
        } else {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        return filteredPlaylist;
    }

    // find a playlist by its ID and verify ownership or shared access
    @Override
    public Playlist findPlaylistByIdAndOwner(Long id, String ownerUsername) {
        if (id == null || ownerUsername == null) {
            return null;
        }

        return playlistRepository.findById(id)
                .filter(playlist -> playlist.getOwnerUsername().equals(ownerUsername) ||
                        (playlist.getSharedWithUsers() != null
                                && playlist.getSharedWithUsers().contains(ownerUsername)))
                .orElse(null);
    }

    // create a new playlist for a user
    @Override
    public Playlist createPlaylist(String name, String ownerUsername) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Playlist name cannot be null or empty");
        }

        userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Playlist playlist = new Playlist();
        playlist.setName(name.trim());
        playlist.setOwnerUsername(ownerUsername);
        playlist.setCreatedAt(LocalDateTime.now());

        return playlistRepository.save(playlist);
    }

    // add a track to a playlist
    @Override
    public void addTrackToPlaylist(Long playlistId, Long trackId) {
        playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found"));

        audiotrackRepository.findById(trackId)
                .orElseThrow(() -> new ResourceNotFoundException("Track not found"));

        int position = (int) playlistItemRepository.countByPlaylistId(playlistId);

        PlaylistItem item = new PlaylistItem();
        item.setPlaylistId(playlistId);
        item.setTrackId(trackId);
        item.setPosition(position);

        playlistItemRepository.save(item);
    }

    // remove a track from a playlist
    @Override
    public void removeTrackFromPlaylist(Long playlistId, Long trackId) {
        // verify playlist exists
        playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found"));

        // verify track exists
        audiotrackRepository.findById(trackId)
                .orElseThrow(() -> new ResourceNotFoundException("Track not found"));

        List<PlaylistItem> items = playlistItemRepository.findByPlaylistIdOrderByPositionAsc(playlistId);

        for (PlaylistItem i : items) {
            if (i.getTrackId().equals(trackId)) {
                playlistItemRepository.delete(i);
            }
        }
    }

    // rate a specific track with a star rating
    @Override
    public void rateTrack(String username, Long trackId, int stars) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (trackId == null) {
            throw new IllegalArgumentException("Track ID cannot be null");
        }
        if (stars < 0 || stars > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }

        Rating rating = ratingRepository.findByUsernameAndTrackId(username, trackId)
                .orElse(new Rating(username, trackId, 0));

        rating.setStars(stars);
        ratingRepository.save(rating);
    }

    // toggle a track as favorite for a user
    @Override
    public void toggleFavorite(String username, Long trackId) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (trackId == null) {
            throw new IllegalArgumentException("Track ID cannot be null");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Audiotrack track = audiotrackRepository.findById(trackId)
                .orElseThrow(() -> new ResourceNotFoundException("Track not found"));

        favoriteRepository.findByUserAndTrack(user, track)
                .ifPresentOrElse(
                        favorite -> favoriteRepository.delete(favorite),
                        () -> favoriteRepository.save(new FavouriteTrack(user, track)));
    }

    // get all favorite track IDs for a specific user
    @Override
    public Set<Long> getUserFavorites(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Set<Long> trackIds = new HashSet<>();
        for (FavouriteTrack favorite : favoriteRepository.findByUser(user)) {
            trackIds.add(favorite.getTrack().getId());
        }
        return trackIds;
    }

    // save or update a playlist
    @Override
    public Playlist savePlaylist(Playlist playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist cannot be null");
        }
        return playlistRepository.save(playlist);
    }

    // get all playlists shared with a specific user
    @Override
    public List<Playlist> getSharedPlaylistsForUser(String username) {
        return playlistRepository.findBySharedWithUsersContaining(username);
    }

    // get all tracks sorted by title
    @Override
    public List<Audiotrack> getAllTracksSorted() {
        return audiotrackRepository.findAll(Sort.by(Sort.Order.asc("title").ignoreCase()));
    }

    // find a playlist by its ID
    @Override
    public Playlist findPlaylistById(Long id) {
        return playlistRepository.findById(id).orElse(null);
    }

    // check if a user can access a playlist
    @Override
    public boolean canUserAccessPlaylist(Long playlistId, String username) {
        return playlistRepository.findById(playlistId)
            .filter(p -> p.getOwnerUsername().equals(username)
                || p.getOwnerUsername().equals("demo")
                || (p.getSharedWithUsers() != null && p.getSharedWithUsers().contains(username)))
            .isPresent();
    }

    // add a user to the shared list of a playlist
    @Override
    public void addUserToSharedPlaylist(Long playlistId, String username) {
        Playlist playlist = findPlaylistById(playlistId);
        if (playlist != null) {
            if (playlist.getSharedWithUsers() == null) {
                playlist.setSharedWithUsers(new HashSet<>());
            }
            playlist.getSharedWithUsers().add(username);
            savePlaylist(playlist);
        }
    }

    // get playlist items for a playlist
    public List<PlaylistItem> getPlaylistItems(Long playlistId) {
        return playlistItemRepository.findByPlaylistIdOrderByPositionAsc(playlistId);
    }

    // get tracks by their IDs
    public List<Audiotrack> getTracksByIds(Set<Long> trackIds) {
        return audiotrackRepository.findAllById(trackIds);
    }

    // check if track exists
    public boolean trackExists(Long trackId) {
        return audiotrackRepository.existsById(trackId);
    }

    // count playlist items
    public long countPlaylistItems(Long playlistId) {
        return playlistItemRepository.countByPlaylistId(playlistId);
    }

    // save playlist item
    public void savePlaylistItem(PlaylistItem item) {
        playlistItemRepository.save(item);
    }

    // get stored user ratings
    @Override
    public Map<Long, Integer> getUserRatings(String username) {
        Map<Long, Integer> ratingsMap = new HashMap<>();
        for (Rating r : ratingRepository.findByUsername(username)) {
            ratingsMap.put(r.getTrackId(), r.getStars());
        }
        return ratingsMap;
    }

    // get user'sfavorite artist names
    @Override
    public Set<String> getFavoriteArtistNames(String username) {
        Set<String> artistNames = new HashSet<>();
        for (FavouriteArtist fa : favouriteArtistRepository.findByUsername(username)) {
            artistNames.add(fa.getArtist());
        }
        return artistNames;
    }

    // toggle artist favorite for user
    @Override
    public void toggleArtistFavorite(String username, String artist) {
        if (artist == null || artist.isBlank()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty");
        }
        
        favouriteArtistRepository.findByUsernameAndArtist(username, artist)
            .ifPresentOrElse(
                existing -> favouriteArtistRepository.delete(existing),
                () -> favouriteArtistRepository.save(new FavouriteArtist(username, artist))
            );
    }

    // save track
    @Override
    public Audiotrack saveTrack(Audiotrack track) {
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null");
        }
        return audiotrackRepository.save(track);
    }

}
