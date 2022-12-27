package com.sparta.lineclone.dto;

import com.sparta.lineclone.entity.User;
import lombok.Getter;

@Getter
public class UserInfo {
    private String username;
    private String nickname;

    public UserInfo(User user){
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }
}
