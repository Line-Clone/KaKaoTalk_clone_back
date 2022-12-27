package com.sparta.lineclone.event.handler;

import com.sparta.lineclone.event.ChatedEvent;
import com.sparta.lineclone.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @EventListener
    public void saveChatRoom(ChatedEvent event) {
        chatRoomRepository.save(event.getChatRoom());
    }
}
