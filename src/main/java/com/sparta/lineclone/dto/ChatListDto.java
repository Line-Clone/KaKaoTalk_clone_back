package com.sparta.lineclone.dto;

import com.sparta.lineclone.entity.ChatRoom;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChatListDto {

    private Long id;
    private String roomId;
    private String roomName;
    private String createUserName;

    public ChatListDto(ChatRoom chatRoom){
        this.id = chatRoom.getId();
        this.roomId = chatRoom.getRoomId();
        this.roomName = chatRoom.getRoomName();
        this.createUserName = chatRoom.getCreateUserName();
    }

}
