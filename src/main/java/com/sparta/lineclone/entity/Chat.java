package com.sparta.lineclone.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Chat extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    public Chat(ChatRoom chatRoom, String roomId, ChatMessage message) {
        this.roomId = roomId;
        this.message = message.getMessage();
        this.chatRoom = chatRoom;
    }
}