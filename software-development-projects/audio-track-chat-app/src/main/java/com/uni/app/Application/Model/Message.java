package com.uni.app.Application.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String sender;
    private String recipient;
    private String body;
    private LocalDateTime sentAt;

    public Message(String sender, String recipient, String body, LocalDateTime sentAt) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
        this.sentAt = sentAt;
    }
}