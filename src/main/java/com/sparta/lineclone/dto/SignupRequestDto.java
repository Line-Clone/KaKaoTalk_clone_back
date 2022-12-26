package com.sparta.lineclone.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SignupRequestDto {

//    @Size(min = 4,max = 10,message ="아이디는 4에서 10자 사이 입니다.")
//    @Pattern(regexp = "/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i",message = "아이디 형식이 일치하지 않습니다.")
    private String username;

//    @Size(min = 8,max = 15,message ="비밀번호는 8에서 15자 사이 입니다.")
//    @Pattern(regexp = "/(?=.*\\d{1,50})(?=.*[~`!@#$%\\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,16}$/",message = "비밀번호 형식이 일치하지 않습니다.")
    private String password;

    private String nickname;

    private boolean admin = false;  //admin 인지 아닌지 확인
    private String adminToken = "";
}