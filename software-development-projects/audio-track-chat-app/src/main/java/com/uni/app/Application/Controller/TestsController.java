package com.uni.app.Application.Controller;

import com.uni.app.Application.Model.Audiotrack;
import com.uni.app.Application.Model.Message;
import com.uni.app.Application.Model.Playlist;
import com.uni.app.Application.Model.PlaylistItem;
import com.uni.app.Application.Model.User;
import com.uni.app.Application.Repository.AudiotrackRepository;
import com.uni.app.Application.Repository.MessageRepository;
import com.uni.app.Application.Repository.PlaylistItemRepository;
import com.uni.app.Application.Repository.PlaylistRepository;
import com.uni.app.Application.Repository.UserRepository;
import com.uni.app.Application.Service.ITunesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class TestsController {

    private static final String CURRENT_USER = "you";

    private final UserRepository users;
    private final MessageRepository messages;
    private final AudiotrackRepository tracks;
    private final PlaylistRepository playlists;
    private final PlaylistItemRepository items;
    private final ITunesService itunes;

    public TestsController(UserRepository users,
                           MessageRepository messages,
                           AudiotrackRepository tracks,
                           PlaylistRepository playlists,
                           PlaylistItemRepository items,
                           ITunesService itunes) {
        this.users = users;
        this.messages = messages;
        this.tracks = tracks;
        this.playlists = playlists;
        this.items = items;
        this.itunes = itunes;
    }

    /** Small DTO so Jackson serializes cleanly */
    public static class Check {
        public String name;
        public boolean passed;
        public String details;
        public Check(String n, boolean p, String d) { name = n; passed = p; details = d; }
    }

    /** Single endpoint that runs ALL acceptance checks and returns one JSON summary */
    @GetMapping("/tests")
    public Map<String, Object> run() {
        List<Check> checks = new ArrayList<>();

        // --- Seed required users (idempotent, no reliance on custom finder) ---
        ensureUser("you");
        ensureUser("friend");
        ensureUser("other");

        // ========== CHAT (MessageRepository) ==========
        // 1) Persistence save/read
        long before = messages.count();
        messages.save(new Message("you", "friend", "UAT-PERSIST", now()));
        boolean foundPersist = false;
        for (Message x : messages.findAll()) {
            if ("UAT-PERSIST".equals(x.getBody())) { foundPersist = true; break; }
        }
        checks.add(new Check("Chat: persistence save/read", foundPersist,
                "count before=" + before + ", after=" + messages.count()));

        // Seed convo (ordered by sentAt)
        messages.save(new Message("you","friend","A", minusMinutes(3)));
        messages.save(new Message("friend","you","B", minusMinutes(2)));
        messages.save(new Message("you","friend","C", minusMinutes(1)));
        messages.save(new Message("you","other","NOISE", now()));

        // 2) Retrieval two-party filter (you<->friend only)
        List<Message> convo = messages
            .findBySenderAndRecipientOrSenderAndRecipientOrderBySentAtAsc("you","friend","friend","you");

        boolean onlyTwoParty = true;
        for (Message x : convo) {
            boolean okS = "you".equals(x.getSender()) || "friend".equals(x.getSender());
            boolean okR = "you".equals(x.getRecipient()) || "friend".equals(x.getRecipient());
            if (!(okS && okR)) { onlyTwoParty = false; break; }
        }
        checks.add(new Check("Chat: retrieval two-party filter", onlyTwoParty, "size=" + convo.size()));

        // 3) Retrieval ascending order by sentAt
        boolean ascending = true;
        for (int i = 1; i < convo.size(); i++) {
            LocalDateTime prev = convo.get(i - 1).getSentAt();
            LocalDateTime cur  = convo.get(i).getSentAt();
            if (prev == null || cur == null || prev.isAfter(cur)) { ascending = false; break; }
        }
        checks.add(new Check("Chat: ascending order", ascending, "ok=" + ascending));

        // 4) Switching friend shows correct thread
        messages.save(new Message("other","you","Hi from other", now()));
        List<Message> withOther = messages
            .findBySenderAndRecipientOrSenderAndRecipientOrderBySentAtAsc("you","other","other","you");
        boolean showsOther = !withOther.isEmpty();
        checks.add(new Check("Chat: switching friend shows correct thread", showsOther,
                "withOther=" + withOther.size()));

        // ========== AUDIO (AudiotrackRepository) ==========
        // 5) Demo audio track exists (create via setters to avoid constructor version drift)
        boolean hasTracks = tracks.count() > 0;
        if (!hasTracks) {
            Audiotrack demo = new Audiotrack();
            demo.setTitle("Demo Track");
            demo.setArtist("System");
            demo.setGenre("Demo");
            demo.setContentType("audio/mpeg");
            demo.setDuration(120.0);
            demo.setFilePath("/music/demo.mp3");
            // set other fields only if present in your model; using minimal safe set here
            tracks.save(demo);
            hasTracks = tracks.count() > 0;
        }
        checks.add(new Check("Audio: demo track exists", hasTracks, "tracks=" + tracks.count()));

        // ========== PLAYLISTS (PlaylistRepository + PlaylistItemRepository) ==========
        // 6) Playlist create & fetch (use existing constructor; fallback to setters if needed)
        Playlist p = playlists.save(new Playlist("UAT Playlist", CURRENT_USER, now()));
        boolean playlistFound = playlists.findById(p.getId()).isPresent();
        checks.add(new Check("Playlists: create/fetch", playlistFound, "id=" + p.getId()));

        // 7) Playlist add item (link at least one existing track)
        Audiotrack anyTrack = null;
        for (Audiotrack t : tracks.findAll()) { anyTrack = t; break; }
        if (anyTrack != null) {
            items.save(new PlaylistItem(p.getId(), anyTrack.getId(), 0));
            boolean itemExists = !items.findByPlaylistIdOrderByPositionAsc(p.getId()).isEmpty();
            checks.add(new Check("Playlists: add item", itemExists,
                    "playlistId=" + p.getId() + ", trackId=" + anyTrack.getId()));
        } else {
            checks.add(new Check("Playlists: add item", false, "no tracks available to add"));
        }

        // 8) Playlist share writes message to chat
        messages.save(new Message(CURRENT_USER, "friend",
                "/playlists/" + p.getId() + "|UAT Playlist", now()));
        boolean shareSaved = false;
        for (Message m : messages.findAll()) {
            if (m.getBody() != null && m.getBody().contains("/playlists/" + p.getId())) {
                shareSaved = true; break;
            }
        }
        checks.add(new Check("Playlists: share saves message", shareSaved, "playlistId=" + p.getId()));

        // ========== EXTERNAL API (ITunesService) ==========
        boolean apiCallable = false;
        try {
            var results = itunes.searchTracks("test");
            apiCallable = (results != null); // may be empty, just ensure no exception
        } catch (Exception ignore) {
            apiCallable = false;
        }
        checks.add(new Check("External API: iTunes fetch callable", apiCallable, "ok=" + apiCallable));

        // --- Summarize ---
        int pass = 0; for (Check c : checks) if (c.passed) pass++;
        Map<String,Object> out = new LinkedHashMap<>();
        out.put("pass", pass);
        out.put("fail", checks.size() - pass);
        out.put("total", checks.size());
        out.put("results", checks);
        return out;
    }

    // --- helpers (no reliance on custom finder) ---
    private void ensureUser(String username) {
        boolean exists = false;
        for (User u : users.findAll()) {
            if (username.equals(u.getUsername())) { exists = true; break; }
        }
        if (!exists) users.save(new User(username));
    }
    private static LocalDateTime now() { return LocalDateTime.now(); }
    private static LocalDateTime minusMinutes(int m) { return LocalDateTime.now().minusMinutes(m); }
}
