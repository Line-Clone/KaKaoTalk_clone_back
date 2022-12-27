package com.sparta.lineclone.dto;

import com.sparta.lineclone.entity.ChatRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChatRoomListResponseDto {
    private UserInfo userInfo;
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    public ChatRoomListResponseDto(UserInfo user, List<ChatRoom> chatRoomList){
        this.userInfo = user;
        this.chatRoomList = chatRoomList;
    }
}
