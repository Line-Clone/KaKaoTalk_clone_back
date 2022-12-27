package com.sparta.lineclone.dto;

import com.sparta.lineclone.entity.Chat;
import com.sparta.lineclone.entity.ChatMessage;
import com.sparta.lineclone.entity.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class ChatMessageDto {

    Optional<Chat> chatList;

    public ChatMessageDto(Optional<Chat> chatList) {
        this.chatList = chatList;
    }

}
