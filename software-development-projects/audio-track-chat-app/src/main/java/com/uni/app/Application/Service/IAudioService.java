package com.uni.app.Application.Service;

import com.uni.app.Application.Model.Audiotrack;
import com.uni.app.Application.Model.Playlist;
import com.uni.app.Application.Model.PlaylistItem;

import java.util.List;
import java.util.Map;
import java.util.Set;

// Service interface for audio-related operations
public interface IAudioService {
    List<Audiotrack> getAllTracks();
    Audiotrack getTrackById(Long id);
    List<Playlist> getUserPlaylists(String username);
    Playlist findPlaylistByIdAndOwner(Long id, String ownerUsername);
    Playlist createPlaylist(String name, String username);
    void addTrackToPlaylist(Long playlistId, Long trackId);
    void removeTrackFromPlaylist(Long playlistId, Long trackId);
    void rateTrack(String username, Long trackId, int rating);
    void toggleFavorite(String username, Long trackId);
    Set<Long> getUserFavorites(String username);
    Playlist savePlaylist(Playlist playlist);
    List<Playlist> getSharedPlaylistsForUser(String username);
    List<Audiotrack> getAllTracksSorted();
    Playlist findPlaylistById(Long id);
    boolean canUserAccessPlaylist(Long playlistId, String username);
    void addUserToSharedPlaylist(Long playlistId, String username);
    List<PlaylistItem> getPlaylistItems(Long playlistId);
    List<Audiotrack> getTracksByIds(Set<Long> trackIds);
    boolean trackExists(Long trackId);
    long countPlaylistItems(Long playlistId);
    void savePlaylistItem(PlaylistItem item);
    Map<Long, Integer> getUserRatings(String username);
    Set<String> getFavoriteArtistNames(String username);
    void toggleArtistFavorite(String username, String artist);
    Audiotrack saveTrack(Audiotrack track);
    
}