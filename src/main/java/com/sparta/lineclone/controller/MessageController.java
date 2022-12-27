package com.sparta.lineclone.controller;

import com.sparta.lineclone.entity.ChatMessage;
import com.sparta.lineclone.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }
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