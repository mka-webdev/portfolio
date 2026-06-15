package com.uni.app.Application;

import com.uni.app.Application.Controller.AudioController;
import com.uni.app.Application.Service.AudioService;
import com.uni.app.Application.Service.ITunesService;

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

@WebMvcTest(AudioController.class)
@WithMockUser(username = "mark", roles = { "USER" })
class AudioControllerTests {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    AudioService audioService;

    @MockitoBean
    ITunesService itunesService;

    @Test
    void addMeta_rejects_whenTitleOrFilePathMissing() throws Exception {
        // Missing title → still redirects (302)
        mvc.perform(post("/audio/addMeta")
                .with(csrf())        
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("title", "")
                .param("filePath", "/music/x.mp3"))                
           .andExpect(status().is3xxRedirection());

        // Missing filePath → redirects again
        mvc.perform(post("/audio/addMeta")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("title", "Demo")
                .param("filePath", ""))                
           .andExpect(status().is3xxRedirection());
    }

    @Test
    void addMeta_accepts_whenAllFieldsPresent() throws Exception {
        mvc.perform(post("/audio/addMeta")
                .with(csrf())
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("title", "Demo")
                .param("filePath", "/music/x.mp3")
                .param("contentType", "audio/mpeg"))
           .andExpect(status().is3xxRedirection());
    }
}
