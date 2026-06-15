package com.uni.app.Application.Service;

import com.uni.app.Application.Model.Message;
import com.uni.app.Application.Repository.MessageRepository;
import com.uni.app.Application.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class ChatService {
    
    private final MessageRepository messageRepository;

    // retrieve all messages for a user, ordered by sent time
    public List<Message> getUserMessages(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return messageRepository.findBySenderOrderBySentAtAsc(username);
    }

    // retrieve messages between two users, ordered by sent time
    public List<Message> getMessagesWithPerson(String you, String them) {
        if (you == null || you.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return messageRepository.findBySenderAndRecipientOrSenderAndRecipientOrderBySentAtAsc(you, them, them, you);
    }

    // find existing chat partners for a user
    public Set<String> getChatPartners(String username) {
        Set<String> partners = new HashSet<>();
        List<Message> messages = messageRepository.findBySenderOrRecipient(username, username);
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        } else {
            for (Message m : messages) {
                if (m.getSender().equals(username)) {
                    partners.add(m.getRecipient());
                } else {
                    partners.add(m.getSender());
                }
            }
        }
        return partners;
    }

    // Send a message from one user to another
    public Message sendMessage(String from, String to, String body) {
        if (from == null || to == null || body == null) {
            throw new IllegalArgumentException("From, to, and body must not be null");
        }
        Message message = new Message();
        message.setSender(from);
        message.setRecipient(to);
        message.setBody(body);
        message.setSentAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // retrieve a specific message by its ID
    public Message getMessageById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Message ID cannot be null");
        }
        return messageRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
    }

    // Save a message
    public Message saveMessage(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        return messageRepository.save(message);
    }
}