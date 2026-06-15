package com.uni.app.Application.Config;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // in-memory user details service with three users
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = org.springframework.security.core.userdetails.User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails friend = org.springframework.security.core.userdetails.User.builder()
                .username("friend")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        // test users:
        UserDetails user1 = org.springframework.security.core.userdetails.User.builder()
                .username("user1")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user2 = org.springframework.security.core.userdetails.User.builder()
                .username("user2")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user3 = org.springframework.security.core.userdetails.User.builder()
                .username("user3")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user4 = org.springframework.security.core.userdetails.User.builder()
                .username("user4")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user5 = org.springframework.security.core.userdetails.User.builder()
                .username("user5")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user6 = org.springframework.security.core.userdetails.User.builder()
                .username("user6")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user7 = org.springframework.security.core.userdetails.User.builder()
                .username("user7")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user8 = org.springframework.security.core.userdetails.User.builder()
                .username("user8")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user9 = org.springframework.security.core.userdetails.User.builder()
                .username("user9")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user10 = org.springframework.security.core.userdetails.User.builder()
                .username("user10")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin, friend, user1, user2, user3, user4, user5, user6, user7,
                user8, user9, user10);
    }

    // Put current user into your existing HttpSession attribute "currentUser"
    @Bean
    public AuthenticationSuccessHandler setSessionUserOnLogin() {
        return (request, response, authentication) -> {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentUser", authentication.getName());
            String next = request.getParameter("next");
            String target = (next == null || next.isBlank()) ? "/chat" : next;
            response.sendRedirect(target);
        };
    }

    // security filter chain configuration
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
            AuthenticationSuccessHandler successHandler) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index",
                                "/login", "/whoami",
                                "/styles/**", "/music/**",
                                "/static/**","/tests")
                        .permitAll()
                        .requestMatchers("/audio/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/playlists/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login") // let Spring Security handle POST /login
                        .successHandler(successHandler)
                        .permitAll())
                // Enable Spring Security logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());

        return http.build();
    }

    // custom HTTP firewall to restrict methods and hostnames
    @Bean
    public HttpFirewall httpFirewall() {
        StrictHttpFirewall fw = new StrictHttpFirewall();
        fw.setAllowedHttpMethods(List.of("GET", "POST"));
        fw.setAllowedHostnames(h -> "localhost".equalsIgnoreCase(h) || "127.0.0.1".equals(h));
        return fw;
    }

    // spring security to use the firewall */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(HttpFirewall firewall) {
        return web -> web.httpFirewall(firewall);
    }
}