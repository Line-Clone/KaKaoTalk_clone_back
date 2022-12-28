package com.sparta.lineclone.controller;

import com.sparta.lineclone.entity.Chat;
import com.sparta.lineclone.entity.ChatMessage;
import com.sparta.lineclone.entity.ChatRoom;
import com.sparta.lineclone.repository.ChatRepository;
import com.sparta.lineclone.repository.ChatRoomRepository;

import com.sparta.lineclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MessageController {
    @Autowired
    ApplicationEventPublisher publisher;
    private final SimpMessageSendingOperations sendingOperations;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;


    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        String type = "TALK";
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            type = "ENTER";

            message.setMessage(message.getSender() + "님이 입장하였습니다.");

            sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
        } else {
            sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);

            ChatRoom chatRoom = chatRoomRepository.findByRoomId(message.getRoomId()).orElseThrow(
                    () -> new RuntimeException("채팅방이 존재하지 않습니다.")
            );
            Chat chat = new Chat(chatRoom, message.getRoomId(), message, type);
            chatRepository.save(chat);
        }
    }


}