package com.uni.app.Application.Model;

import lombok.Data;

// DTO for message requests
@Data
public class MessageRequest {
    private String from;
    private String to;
    private String content;
}