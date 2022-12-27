package com.sparta.lineclone.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ChatRoomListResponseDto {
    private UserInfo userInfo;
    private List<ChatListDto> chatRoomList;
    public ChatRoomListResponseDto(UserInfo user, List<ChatListDto> chatRoomList){
        this.userInfo = user;
        this.chatRoomList = chatRoomList;
    }
}
