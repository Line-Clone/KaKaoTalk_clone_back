package com.sparta.lineclone.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomId;
    private String roomName;
    private String createUserName;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<Chat> chatList = new ArrayList<>();

    public ChatRoom(){}
    public ChatRoom(ChatRoom chatRoom){
        this.roomId = chatRoom.getRoomId();
        this.roomName = chatRoom.getRoomName();
//        this.createUserName=user.getNickname();
    }
    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        return room;
    }
}