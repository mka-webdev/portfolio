package com.uni.app.Application.Controller;

import com.uni.app.Application.Model.User;
import com.uni.app.Application.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class DevWhoAmIController {

    private final UserRepository users;

    // GET /whoami -> show a tiny form in the browser
    @GetMapping("/whoami")
    @ResponseBody
    public String page() {
        return """
            <html><body>
              <form method='post' action='/whoami'>
                <label>Username: <input name='username'/></label>
                <button type='submit'>Set user</button>
              </form>
            </body></html>
            """;
    }
    
    // POST /whoami -> set the session user
    @PostMapping("/whoami")
    public String set(@RequestParam String username,
                      @RequestParam(required = false) String next,
                      HttpSession session) {
        String me = username.trim();
        session.setAttribute("currentUser", me);

        // make sure user exists in DB
        users.findByUsername(me).orElseGet(() -> users.save(new User(me)));

        return "redirect:" + (next == null ? "/chat" : next);
    }
}
