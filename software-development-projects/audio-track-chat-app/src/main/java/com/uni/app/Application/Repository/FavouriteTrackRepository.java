package com.uni.app.Application.Repository;

import com.uni.app.Application.Model.Audiotrack;
import com.uni.app.Application.Model.FavouriteTrack;
import com.uni.app.Application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FavouriteTrackRepository extends JpaRepository<FavouriteTrack, Long> {
    List<FavouriteTrack> findByUser(User user);

    Optional<FavouriteTrack> findByUserAndTrack(User user, Audiotrack track);

    @Transactional
    void deleteByUserAndTrack(User user, Audiotrack track);
}
