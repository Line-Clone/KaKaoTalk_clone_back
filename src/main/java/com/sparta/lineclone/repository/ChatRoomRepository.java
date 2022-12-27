package com.sparta.lineclone.repository;

import com.sparta.lineclone.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<com.sparta.lineclone.entity.ChatRoom, Long> {
    Optional<ChatRoom> findByRoomId(String RoomId);
}
