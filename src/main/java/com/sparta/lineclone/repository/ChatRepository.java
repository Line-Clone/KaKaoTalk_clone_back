package com.sparta.lineclone.repository;

import com.sparta.lineclone.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
