package com.allen.learningbootwebsocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @author JUN
 * @Description TODO
 * @createTime 13:16
 */
//@Configuration
@EnableWebSocket
public class WebsocketConfiguration implements WebSocketConfigurer {
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "myWebSocket")
            .addInterceptors(myHandshakeInterceptor())
            .withSockJS();
    }
    
    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler();
    }
    
    @Bean
    public HandshakeInterceptor myHandshakeInterceptor() {
        return new MyHandshakeInterceptor();
    }
}
