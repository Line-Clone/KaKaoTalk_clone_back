package com.sparta.lineclone.controller;

import com.sparta.lineclone.entity.ChatRoom;
import com.sparta.lineclone.security.UserDetailsImpl;
import com.sparta.lineclone.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "chat/room";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    } // -> 아이디 값 기준으로 방 검색

    // 채팅방 생성 -> 친구추가를 할 때에 방이 같이 만들어져야 할듯? 아님말고
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {

        return chatService.createRoom(name);
    }

    // 채팅방 입장 화면 -> 룸id가 아니라 친구 id를 넣어서 일치하는 방을 입장?
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "chat/roomdetail";
    }
    // 특정 채팅방 조회 이게 그건가..?
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }
}