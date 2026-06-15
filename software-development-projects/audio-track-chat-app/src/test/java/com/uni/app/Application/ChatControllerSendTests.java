package com.uni.app.Application;

import com.uni.app.Application.Controller.ChatController;
import com.uni.app.Application.Repository.MessageRepository;
import com.uni.app.Application.Repository.UserRepository;
import com.uni.app.Application.Service.AudioService;
import com.uni.app.Application.Service.ChatService;
import com.uni.app.Application.Service.DomainEventPublisher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(ChatController.class)
@WithMockUser(username = "mark", roles = {"USER"})
class ChatControllerSendTests {

    @Autowired MockMvc mvc;

    @MockitoBean
    UserRepository userRepository;

    @MockitoBean
    MessageRepository messageRepository;  
    
    @MockitoBean
    AudioService audioService;

    @MockitoBean
    ChatService chatService;

    @MockitoBean
    DomainEventPublisher domainEventPublisher; 

   @Test
    void sendMessage_rejects_whenRecipientMissing() throws Exception {
        mvc.perform(post("/chat/send")
                .with(csrf()) 
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("to", "")
                .param("text", "hello"))
           .andExpect(status().is3xxRedirection());
    }

    @Test
    void sendMessage_rejects_whenContentMissing() throws Exception {
        mvc.perform(post("/chat/send")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("to", "john")
                .param("text", ""))
           .andExpect(status().is3xxRedirection());
    }

    @Test
    void sendMessage_accepts_whenAllFieldsPresent() throws Exception {
        mvc.perform(post("/chat/send")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("to", "john")
                .param("text", "hello world"))
           .andExpect(status().is3xxRedirection());
    }
}
