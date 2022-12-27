package com.sparta.lineclone.event;

import com.sparta.lineclone.entity.ChatMessage;
import com.sparta.lineclone.entity.ChatRoom;

public class ChatRoomtedEvent {
    private String roomId;
    private ChatMessage message;

    public ChatRoomtedEvent(String roomId, ChatMessage message) {
        this.roomId = roomId;
        this.message = message;
    }

//    public String get
////    public ChatRoom getChatRoom() {
////        return chatRoom;
////    }
}
