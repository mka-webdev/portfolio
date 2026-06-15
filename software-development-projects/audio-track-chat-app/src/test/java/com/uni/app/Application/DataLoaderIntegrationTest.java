package com.uni.app.Application;

import com.uni.app.Application.Repository.UserRepository;
import com.uni.app.Application.Repository.MessageRepository;
import com.uni.app.Application.Repository.AudiotrackRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest 
@Transactional   
class DataLoaderIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AudiotrackRepository audiotrackRepository;

    @Test
    void dataLoader_shouldInsertDemoUsers() {
        assertThat(userRepository.count())
                .as("Users should be preloaded by DataLoader")
                .isGreaterThan(0);
    }

    @Test
    void dataLoader_shouldInsertDemoMessages() {
        assertThat(messageRepository.count())
                .as("Messages should be preloaded by DataLoader")
                .isGreaterThan(0);
    }

    @Test
    void dataLoader_shouldInsertDemoTracks() {
        assertThat(audiotrackRepository.count())
                .as("Tracks should be preloaded by DataLoader")
                .isGreaterThan(0);
    }
}
