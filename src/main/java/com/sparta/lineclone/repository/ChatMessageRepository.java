package com.sparta.lineclone.repository;

import com.sparta.lineclone.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
