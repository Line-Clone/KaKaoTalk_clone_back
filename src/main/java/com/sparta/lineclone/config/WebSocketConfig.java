package com.sparta.lineclone.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@RequiredArgsConstructor //의존성 주입
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {             // 메세지를 중간에서 라우팅 할 때 사용하는 메세지 브로커 구성

        // enableSimpleBroker : 해당 주소를 구독하는 클라이언트에게 메세지를 보낸다
        //즉, 인자에는 구독 요청의 prefix를 넣고, 클라이언트에서 1번 채널을 구독하고자 할 때는 /sub/1 형식과 같은 규칙을 따라야 한다.
        config.enableSimpleBroker("/topic");

        // setApplicationDestinationPrefixes에는 메시지 발행 요청의 prefix를 넣는다. 즉, /pub로 시작하는 메시지만 해당 Broker에서 받아서 처리한다.
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //클라이언트에서 WebSocket에 접속할 수 있는 endpoint를 지정한다.
        registry.addEndpoint("/gs-guide-websocket")
                .setAllowedOrigins("*")
                .withSockJS();
    }


}