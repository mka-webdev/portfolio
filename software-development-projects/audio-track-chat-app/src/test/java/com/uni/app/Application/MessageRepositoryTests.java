package com.uni.app.Application;

import com.uni.app.Application.Model.Message;
import com.uni.app.Application.Repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MessageRepositoryTests {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void savesAndFindsMessageById() {
        Message m = new Message("alice", "bob", "hello", LocalDateTime.now());

        Message saved = messageRepository.saveAndFlush(m);

        assertThat(saved.getId()).isNotNull();
        assertThat(messageRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void findsConversationBetweenTwoUsers() {
        Message m1 = new Message("alice", "bob", "hi bob", LocalDateTime.now().minusMinutes(2));
        Message m2 = new Message("bob", "alice", "hi alice", LocalDateTime.now().minusMinutes(1));

        messageRepository.save(m1);
        messageRepository.save(m2);

        List<Message> conversation =
            messageRepository.findBySenderAndRecipientOrSenderAndRecipientOrderBySentAtAsc(
                    "alice", "bob", "bob", "alice");

        assertThat(conversation).hasSize(2);
        assertThat(conversation.get(0).getBody()).isEqualTo("hi bob");
        assertThat(conversation.get(1).getBody()).isEqualTo("hi alice");
    }
}
