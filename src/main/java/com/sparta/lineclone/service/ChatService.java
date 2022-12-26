package com.sparta.lineclone.service;

import com.sparta.lineclone.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }    //의존관게 주입완료되면 실행되는 코드

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {                     //채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoom findById(String roomId) {                 //채팅방 하나 불러오기
        return chatRooms.get(roomId);
    }
    public ChatRoom createRoom(String name) {                   //채팅방 생성
        ChatRoom chatRoom = ChatRoom.create(name);

        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}