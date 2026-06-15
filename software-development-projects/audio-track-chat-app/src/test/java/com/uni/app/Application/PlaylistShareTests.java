package com.uni.app.Application;

import com.uni.app.Application.Controller.PlaylistController;
import com.uni.app.Application.Model.Playlist;
import com.uni.app.Application.Repository.AudiotrackRepository;
import com.uni.app.Application.Repository.MessageRepository;
import com.uni.app.Application.Repository.PlaylistItemRepository;
import com.uni.app.Application.Repository.PlaylistRepository;
import com.uni.app.Application.Config.SecurityConfig;
import com.uni.app.Application.Service.AudioService;
import com.uni.app.Application.Service.ChatService;
import com.uni.app.Application.Service.DomainEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaylistController.class)
@Import(SecurityConfig.class)
@WithMockUser(username = "mark", roles = {"USER"})
class PlaylistShareTests {

    @Autowired MockMvc mvc;

    @MockitoBean PlaylistRepository playlists;
    @MockitoBean PlaylistItemRepository items;
    @MockitoBean AudiotrackRepository tracks;
    @MockitoBean MessageRepository messages;
    @MockitoBean DomainEventPublisher events;
    @MockitoBean ChatService chatService;
    @MockitoBean AudioService audioService;

    @Test
    void share_redirects_whenPlaylistExists() throws Exception {
        Playlist p = new Playlist("My First Playlist", "testuser", LocalDateTime.now());
        p.setId(42L);
        when(playlists.findById(42L)).thenReturn(Optional.of(p));

        mvc.perform(post("/playlists/42/share")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .sessionAttr("currentUser", "mark")
                .param("to", "bob")
                .param("from", "mark"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/playlists"));
    }

    @Test
    void share_redirects_whenPlaylistNotFound() throws Exception {
        when(playlists.findById(99L)).thenReturn(Optional.empty());

        mvc.perform(post("/playlists/99/share")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .sessionAttr("currentUser", "mark")
                .param("to", "bob")
                .param("from", "mark"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/playlists"));
    }

    @Test
    void share_redirects_evenWhenRecipientBlank() throws Exception {
        Playlist p = new Playlist("Empty To Test", "testuser", LocalDateTime.now());
        p.setId(7L);
        when(playlists.findById(7L)).thenReturn(Optional.of(p));

        mvc.perform(post("/playlists/7/share")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .sessionAttr("currentUser", "mark")
                .param("to", "   ")
                .param("from", "mark"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/playlists"));
    }
}
