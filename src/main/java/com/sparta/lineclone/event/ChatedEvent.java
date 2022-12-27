package com.sparta.lineclone.event;

import com.sparta.lineclone.entity.ChatMessage;
import com.sparta.lineclone.entity.ChatRoom;

public class ChatedEvent {

    private ChatRoom chatRoom;

    public ChatedEvent(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }
}
