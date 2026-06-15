package com.uni.app.Application.Repository;

import com.uni.app.Application.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUsernameAndTrackId(String username, Long trackId);

    List<Rating> findByUsername(String username);
}
