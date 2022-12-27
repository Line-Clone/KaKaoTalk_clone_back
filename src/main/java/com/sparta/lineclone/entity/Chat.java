package com.sparta.lineclone.entity;

import javax.persistence.*;

@Entity
public class Chat extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String userId;
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
