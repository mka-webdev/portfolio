package com.uni.app.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uni.app.Application.Model.PlaylistItem;
import java.util.List;

public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Long> {
    List<PlaylistItem> findByPlaylistIdOrderByPositionAsc(Long playlistId);
    long countByPlaylistId(Long playlistId);
}
