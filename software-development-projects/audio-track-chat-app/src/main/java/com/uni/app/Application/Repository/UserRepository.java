package com.uni.app.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uni.app.Application.Model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}