package com.uni.app.Application.Repository;

import com.uni.app.Application.Model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
  
    // Find playlists that a user can see (their own + demo playlists)
    List<Playlist> findBySharedWithUsersContaining(String username);
}
