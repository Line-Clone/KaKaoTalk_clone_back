package com.sparta.lineclone.controller;

import com.sparta.lineclone.dto.ChatMessageDto;
import com.sparta.lineclone.entity.Chat;
import com.sparta.lineclone.entity.ChatMessage;
import com.sparta.lineclone.entity.ChatRoom;
import com.sparta.lineclone.repository.ChatRepository;
import com.sparta.lineclone.repository.ChatRoomRepository;

import com.sparta.lineclone.security.UserDetailsImpl;
import com.sparta.lineclone.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MessageController {
    @Autowired
    ApplicationEventPublisher publisher;
    private final ChatService chatService;
    private final SimpMessageSendingOperations sendingOperations;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;


    @MessageMapping("/chat/message")
    public void enter(ChatMessage message, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        }
        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);


        ChatRoom chatRoom = chatRoomRepository.findByRoomId(message.getRoomId()).orElseThrow(
                () -> new RuntimeException("채팅방이 존재하지 않습니다.")
        );
        Chat chat = new Chat(chatRoom, message.getRoomId(), message);
        chatRepository.save(chat);
//    @MessageMapping("/chat/message")
//    public ResponseEntity<UserInfo> enter(@AuthenticationPrincipal UserDetailsImpl userDetails, ChatMessage message) {
//        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
//            message.setMessage(userDetails.getUser().getNickname()+"님이 입장하였습니다."); // user nickname으로 바꿔야함
//            System.out.println(userDetails.getUser().getNickname()+"님이 입장하였습니다.");
//        }
//        chatService.sendMessage(userDetails.getUser(),message);
//        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
//        return ResponseEntity.ok(new UserInfo(userDetails.getUser()));
//    }
    }


    @GetMapping("/api/room/{roomId}")
    public ResponseEntity<?> getAllMessages(@PathVariable String roomId) {
        return ResponseEntity.ok().body(chatService.getAllMessages(roomId));
    }


}