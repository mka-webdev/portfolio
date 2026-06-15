package com.uni.app.Application;

import com.uni.app.Application.Model.User;
import com.uni.app.Application.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTests {

    @Autowired
    private UserRepository users;

    @Test
    @DisplayName("Save and find by username")
    void savesAndFindsByUsername() {
        User u = new User("mark");
        users.saveAndFlush(u);

        assertThat(u.getId()).isNotNull();
        assertThat(users.findByUsername("mark"))
                .isPresent()
                .get()
                .extracting(User::getUsername)
                .isEqualTo("mark");
    }

    @Test
    @DisplayName("Allows duplicate usernames (no unique constraint on entity)")
    void allowsDuplicateUsernames() {
        users.saveAndFlush(new User("alice"));
        users.saveAndFlush(new User("alice"));

        List<User> all = users.findAll();
        long countAlice = all.stream().filter(x -> "alice".equals(x.getUsername())).count();
        assertThat(countAlice).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Allows null username (no @Column(nullable=false) on entity)")
    void allowsNullUsername() {
        User u = new User(); // no username set
        users.saveAndFlush(u);

        assertThat(u.getId()).isNotNull();
    }
}
