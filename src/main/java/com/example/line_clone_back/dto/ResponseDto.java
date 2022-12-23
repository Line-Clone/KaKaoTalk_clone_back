package com.example.line_clone_back.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto {
    int statusCode;
    String message;

    public ResponseDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}