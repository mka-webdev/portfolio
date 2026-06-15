package com.uni.app.Application;

import com.uni.app.Application.Model.Audiotrack;
import com.uni.app.Application.Repository.AudiotrackRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AudiotrackRepositoryTests {

    @Autowired
    AudiotrackRepository repo;

    @Test
    void savesAndFindsAudiotrackById() {
        // Arrange: create a minimal valid Audiotrack
        Audiotrack t = new Audiotrack();
        t.setTitle("Maple");
        t.setArtist("Dyalla");
        t.setFilePath("/music/Maple - Dyalla.mp3");
        t.setContentType("audio/mpeg");

        // Act: persist
        Audiotrack saved = repo.saveAndFlush(t);

        // Assert: id assigned
        assertThat(saved.getId()).isNotNull();

        // sanity: exactly one record exists
        assertThat(repo.count()).isEqualTo(1);

        // fetch and verify fields
        Audiotrack found = repo.findById(saved.getId()).orElseThrow();
        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getTitle()).isEqualTo("Maple");
        assertThat(found.getArtist()).isEqualTo("Dyalla");
        assertThat(found.getFilePath()).isEqualTo("/music/Maple - Dyalla.mp3");
        assertThat(found.getContentType()).isEqualTo("audio/mpeg");
    }
}
