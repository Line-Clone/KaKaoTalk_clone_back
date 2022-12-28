package com.sparta.lineclone.service;

import com.sparta.lineclone.dto.ChatRoomResponseDto;
import com.sparta.lineclone.dto.MessageResponseDto;
import com.sparta.lineclone.entity.Chat;
import com.sparta.lineclone.dto.ChatListDto;
import com.sparta.lineclone.entity.ChatMessage;
import com.sparta.lineclone.entity.ChatRoom;
import com.sparta.lineclone.entity.User;

import com.sparta.lineclone.event.ChatedEvent;
import com.sparta.lineclone.exception.CustomException;
import com.sparta.lineclone.exception.ErrorCode;

import com.sparta.lineclone.repository.ChatRepository;
import com.sparta.lineclone.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    @Autowired
    ApplicationEventPublisher publisher;
    private Map<String, ChatRoom> chatRooms;
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;

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

    public ChatRoomResponseDto findById(User user, String roomId) {       //채팅방 하나 불러오기
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_MATCH_INFORMATION)
        );

        List<MessageResponseDto> messageList = new ArrayList<>();
        for(Chat messages : chatRoom.getChatList()){
            messageList.add(new MessageResponseDto(messages));
        }

        return new ChatRoomResponseDto(chatRoom, messageList);

    }


    @Transactional
    public ChatRoom createRoom(String name) {                   //채팅방 생성
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomRepository.save(chatRoom);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
//        chatRoomRepository.save(chatRoom);
        publisher.publishEvent(new ChatedEvent(chatRoom));
        return chatRoom;
    }




    public void sendMessage(User user, ChatMessage message) {
//        ChatMessage chatMessage =
    }
}