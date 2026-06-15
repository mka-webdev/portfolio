package com.uni.app.Application;

import com.uni.app.Application.Model.Rating;
import com.uni.app.Application.Repository.RatingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class RatingRepositoryPersistenceTests {

    @Autowired
    private RatingRepository ratings;

    @Test
    @DisplayName("Save and find by (username, trackId)")
    void savesAndFindsByUsernameAndTrackId() {
        Rating r = new Rating("mark", 42L, 3);
        ratings.saveAndFlush(r);

        assertThat(r.getId()).isNotNull();

        assertThat(ratings.findByUsernameAndTrackId("mark", 42L))
                .isPresent()
                .get()
                .extracting(Rating::getStars)
                .isEqualTo(3);
    }

    @Test
    @DisplayName("Unique (username, trackId) prevents duplicates")
    void uniqueConstraintPreventsDuplicateForSameUserAndTrack() {
        Rating first = new Rating("mark", 42L, 4);
        ratings.saveAndFlush(first);

        Rating dup = new Rating("mark", 42L, 5);

        assertThatThrownBy(() -> ratings.saveAndFlush(dup))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("Rejects null username")
    void rejectsNullUsername() {
        Rating r = new Rating(null, 42L, 3);

        assertThatThrownBy(() -> ratings.saveAndFlush(r))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("Rejects null trackId")
    void rejectsNullTrackId() {
        Rating r = new Rating("mark", null, 3);

        assertThatThrownBy(() -> ratings.saveAndFlush(r))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
