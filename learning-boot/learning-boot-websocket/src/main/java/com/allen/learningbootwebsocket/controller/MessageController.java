package com.allen.learningbootwebsocket.controller;

import com.allen.learningbootwebsocket.pojo.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author JUN @Description TODO
 * @createTime 15:53
 */
@Slf4j
@Controller
public class MessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hi")
    public void hi(ChatMessage message) {
        simpMessagingTemplate.convertAndSendToUser("allen", "/topic/hi", message);
    }

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public ChatMessage oneMessageToAll(ChatMessage message) {
        log.info(message.getMessage());
        return new ChatMessage("welcomem");
    }
}
