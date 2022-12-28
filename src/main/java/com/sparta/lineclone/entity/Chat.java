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

    @Column
    private String sender;


    @Column(nullable = false)
    private String messageType;



    public Chat(ChatRoom chatRoom, String roomId, ChatMessage message,String type) {
        this.roomId = roomId;
        this.message = message.getMessage();
        this.chatRoom = chatRoom;
        this.sender = message.getSender();
        this.messageType = type;
    }


}

