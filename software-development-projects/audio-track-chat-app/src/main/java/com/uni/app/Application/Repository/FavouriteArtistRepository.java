package com.uni.app.Application.Repository;

import com.uni.app.Application.Model.FavouriteArtist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteArtistRepository extends JpaRepository<FavouriteArtist, Long> {
    Optional<FavouriteArtist> findByUsernameAndArtist(String username, String artist);

    List<FavouriteArtist> findByUsername(String username);
}
