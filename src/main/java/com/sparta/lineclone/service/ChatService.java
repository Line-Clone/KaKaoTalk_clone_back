package com.sparta.lineclone.service;

import com.sparta.lineclone.dto.ChatListDto;
import com.sparta.lineclone.entity.ChatMessage;
import com.sparta.lineclone.entity.ChatRoom;
import com.sparta.lineclone.entity.User;
import com.sparta.lineclone.exception.CustomException;
import com.sparta.lineclone.exception.ErrorCode;
import com.sparta.lineclone.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;

    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }    //의존관게 주입완료되면 실행되는 코드

    //채팅방 불러오기
    public List<ChatListDto> findAllRoom() {                     //채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = chatRoomRepository.findAll();
        Collections.reverse(result);
        List<ChatListDto> chatList = new ArrayList<>();

        for(ChatRoom chatRoomList : result){
            chatList.add(new ChatListDto(chatRoomList));
        }
        return chatList;
    }

    public ChatRoom findById(String roomId) {       //채팅방 하나 불러오기
        return  chatRoomRepository.findByRoomId(roomId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_MATCH_INFORMATION)
        );
    }


    public ChatRoom createRoom(String name) {                   //채팅방 생성
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomRepository.save(chatRoom);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }


    public void sendMessage(User user, ChatMessage message) {
//        ChatMessage chatMessage =
    }
}