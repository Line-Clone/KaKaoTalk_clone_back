package com.sparta.lineclone.repository;

import com.sparta.lineclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrNickname(String username, String nickname);
    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String nickname);
}