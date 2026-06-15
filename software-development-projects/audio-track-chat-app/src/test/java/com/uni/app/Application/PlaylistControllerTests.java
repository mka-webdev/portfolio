package com.uni.app.Application;

import com.uni.app.Application.Controller.PlaylistController;
import com.uni.app.Application.Repository.AudiotrackRepository;
import com.uni.app.Application.Repository.MessageRepository;
import com.uni.app.Application.Repository.PlaylistItemRepository;
import com.uni.app.Application.Repository.PlaylistRepository;
import com.uni.app.Application.Service.DomainEventPublisher;
import com.uni.app.Application.Service.ChatService; 
import com.uni.app.Application.Service.AudioService; 

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

@WebMvcTest(PlaylistController.class)
@WithMockUser(username = "mark", roles = {"USER"})
class PlaylistControllerTests {

    @Autowired
    MockMvc mvc;

    @MockitoBean PlaylistRepository playlists;
    @MockitoBean PlaylistItemRepository items;
    @MockitoBean AudiotrackRepository tracks;
    @MockitoBean MessageRepository messages;
    @MockitoBean DomainEventPublisher events;
    @MockitoBean ChatService chatService; 
    @MockitoBean AudioService audioService;

    @Test
    void create_rejects_whenNameMissing() throws Exception {
        mvc.perform(post("/playlists/create")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("name", ""))
           .andExpect(status().is3xxRedirection());
    }

    @Test
    void create_accepts_whenNamePresent() throws Exception {
        mvc.perform(post("/playlists/create")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("name", "Roadtrip"))
           .andExpect(status().is3xxRedirection());
    }

    @Test
void addItem_rejects_whenTrackIdMissingOrInvalid() throws Exception {
    //invalid trackId (non-numeric or non-existent) → expect redirect
    mvc.perform(post("/playlists/1/add")
            .with(csrf())
            .contentType(APPLICATION_FORM_URLENCODED)
            .param("trackId", "999"))  // invalid param 
       .andExpect(status().is3xxRedirection());
}

    @Test
    void addItem_accepts_whenTrackIdPresent() throws Exception {
        mvc.perform(post("/playlists/1/add")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("trackId", "1"))
           .andExpect(status().is3xxRedirection());
    }

    @Test
    void share_rejects_whenRecipientMissing() throws Exception {
        mvc.perform(post("/playlists/1/share")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("to", ""))
           .andExpect(status().is3xxRedirection());
    }

    @Test
    void share_accepts_whenRecipientPresent() throws Exception {
        mvc.perform(post("/playlists/1/share")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("to", "alice"))
           .andExpect(status().is3xxRedirection());
    }
}
