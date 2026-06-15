package com.uni.app.Application.Controller;

import com.uni.app.Application.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class LoginController {

    @SuppressWarnings("unused")
    private final UserRepository users; // kept in case you still want it later

    @GetMapping(value = "/", produces = "text/html")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping(value = "/index", produces = "text/html")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "next", required = false) String next,
                            Model model) {
        // where to go after login (default to /chat)
        String nextUrl = (next == null || next.isBlank()) ? "/chat" : next;
        model.addAttribute("next", nextUrl);
        return "login";
    }

}
 