package com.sparta.lineclone.controller;

import com.sparta.lineclone.entity.Chat;
import com.sparta.lineclone.entity.ChatMessage;
import com.sparta.lineclone.entity.ChatRoom;
import com.sparta.lineclone.repository.ChatRepository;
import com.sparta.lineclone.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MessageController {
    @Autowired
    ApplicationEventPublisher publisher;
    private final SimpMessageSendingOperations sendingOperations;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;


    @MessageMapping("/chat/message")
    public void enter(ChatMessage message, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        }
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);


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