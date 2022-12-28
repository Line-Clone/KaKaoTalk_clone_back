package com.sparta.lineclone.dto;


import com.sparta.lineclone.entity.Chat;
import lombok.Getter;

@Getter
public class MessageResponseDto {

    private String sender;
    private String message;

    public MessageResponseDto(Chat messages){
        this.sender = messages.getSender();
        this.message = messages.getMessage();
    }
}
