package com.allen.learningbootwebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author JUN
 * @Description TODO
 * @createTime 15:43
 */
@Configuration
@EnableWebSocketMessageBroker
public class MyWebSocketMessageBrokerConfiguration implements WebSocketMessageBrokerConfigurer {
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp")
            .withSockJS();    // 客户端连接URI
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");  // 订阅主题时的前缀
        registry.setApplicationDestinationPrefixes("/app");  // 客户端send()时要加的前缀
    }
    
}
