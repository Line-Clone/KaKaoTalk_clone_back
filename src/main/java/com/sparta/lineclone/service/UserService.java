package com.sparta.lineclone.service;

import com.sparta.lineclone.dto.LoginRequestDto;
import com.sparta.lineclone.dto.SignupRequestDto;
import com.sparta.lineclone.entity.User;
import com.sparta.lineclone.entity.UserRoleEnum;
import com.sparta.lineclone.exception.CustomException;
import com.sparta.lineclone.jwt.JwtUtil;
import com.sparta.lineclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

import static com.sparta.lineclone.exception.ErrorCode.*;

@Service
@Validated          //추가
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;  //의존성 주입(DI) --> jwtUtil.class 에서 @Component 로 빈을 등록했기때문에 가능
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void signup(@Valid SignupRequestDto signupRequestDto) {      //@Valid 추가 주의!
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());   //저장하기 전에 password 를 Encoder 한다

        Optional<User> found = userRepository.findByUsername(username);  //userRepository 에 구현하기
        if (found.isPresent()) {
            throw new CustomException(DUPLICATED_USERNAME);
        }

        // 사용자 ROLE(권한) 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new CustomException(INVALID_AUTH_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
    }

    //로그인 구현
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER)
        );
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw  new CustomException(NOT_MATCH_INFORMATION);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }
}
