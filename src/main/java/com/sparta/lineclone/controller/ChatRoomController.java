package com.sparta.lineclone.controller;

import com.sparta.lineclone.dto.ChatRoomListResponseDto;
import com.sparta.lineclone.dto.ChatRoomResponseDto;
import com.sparta.lineclone.dto.UserInfo;
import com.sparta.lineclone.entity.ChatRoom;
import com.sparta.lineclone.repository.ChatRoomRepository;
import com.sparta.lineclone.security.UserDetailsImpl;
import com.sparta.lineclone.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    private final ChatRoomRepository chatRoomRepository;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "chat/room";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public ResponseEntity<ChatRoomListResponseDto> room(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(new ChatRoomListResponseDto(new UserInfo(userDetails.getUser()), chatService.findAllRoom()));
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {

        return chatService.createRoom(name);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "chat/roomdetail";
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomResponseDto roomInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String roomId) {
        return chatService.findById(userDetails.getUser(), roomId);
    }
}