package com.sparta.lineclone.dto;

import com.sparta.lineclone.entity.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatRoomResponseDto {

    private Long id;
    private String RoomId;
    private String roomName;
    private String createUserName;

    private List<MessageResponseDto> messsageList = new ArrayList<>();

    public ChatRoomResponseDto(ChatRoom chatRoom, List<MessageResponseDto> messages) {
        this.id = chatRoom.getId();
        this.RoomId = chatRoom.getRoomId();
        this.roomName = chatRoom.getRoomName();
        this.createUserName = chatRoom.getCreateUserName();
        this.messsageList = messages;
    }

}
