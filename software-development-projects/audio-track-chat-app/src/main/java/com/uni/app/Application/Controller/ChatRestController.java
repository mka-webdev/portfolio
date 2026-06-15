package com.uni.app.Application.Controller;

import com.uni.app.Application.Service.ChatService;
import com.uni.app.Application.Model.Message;
import com.uni.app.Application.Model.MessageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@AllArgsConstructor
public class ChatRestController {
    private final ChatService chatService;

    // rest endpoint to get messages for a user, optionally limited by a count
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(
            @RequestParam String username,
            @RequestParam(required = false) Integer limit) {
        try {
            List<Message> messages = chatService.getUserMessages(username);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // rest endpoint to get chat partners for a user
    @PostMapping("/messages")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) {
        try {
            if (request.getFrom() == null || request.getTo() == null || request.getContent() == null) {
                return ResponseEntity.badRequest().build();
            }
            
            Message message = chatService.sendMessage(
                request.getFrom(),
                request.getTo(),
                request.getContent()
            );
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // rest endpoint  to get a specific message by its ID
    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        try {
            Message message = chatService.getMessageById(id);
            if (message == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}