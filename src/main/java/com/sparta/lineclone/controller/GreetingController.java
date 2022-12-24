package com.sparta.lineclone.controller;

import com.sparta.lineclone.dto.HelloMessage;
import com.sparta.lineclone.entity.Greeting;
import com.sparta.lineclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@RequiredArgsConstructor
@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(@AuthenticationPrincipal UserDetailsImpl userDetails, HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        String name = userDetails.getUsername();
        return new Greeting(name+ " : " + HtmlUtils.htmlEscape(message.getName()));
    }

}