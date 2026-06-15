package com.uni.app.Application;

import com.uni.app.Application.Controller.PlaylistController;
import com.uni.app.Application.Repository.AudiotrackRepository;
import com.uni.app.Application.Repository.MessageRepository;
import com.uni.app.Application.Repository.PlaylistItemRepository;
import com.uni.app.Application.Repository.PlaylistRepository;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlaylistController.class)
class AuthenticationTests {

    @Autowired MockMvc mvc;

    @MockitoBean PlaylistRepository playlists;
    @MockitoBean PlaylistItemRepository items;
    @MockitoBean AudiotrackRepository tracks;
    @MockitoBean MessageRepository messages;
    @MockitoBean DomainEventPublisher events;
    @MockitoBean ChatService chatService;
    @MockitoBean AudioService audioService;

    @Test
    void anonymousUser_isUnauthorized() throws Exception {
        mvc.perform(post("/playlists/create")
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("name", "Roadtrip")
                .with(csrf()))
           .andExpect(status().isUnauthorized()); // because Basic Auth, no /login page
    }

    @Test
    @WithMockUser(username = "mark", roles = {"USER"})
    void authenticatedUser_canAccessEndpoint() throws Exception {
        mvc.perform(post("/playlists/create")
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("name", "Roadtrip")
                .with(csrf()))
           .andExpect(status().is3xxRedirection()); // handled by controller
    }
}
