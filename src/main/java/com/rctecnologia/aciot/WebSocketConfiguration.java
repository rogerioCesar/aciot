package com.rctecnologia.aciot;

import org.springframework.context.annotation.Configuration;


import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * Classe utilizada para configuração da comunicação no padrão websocket
 */

@Configuration

@EnableWebSocketMessageBroker
public class WebSocketConfiguration  implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp-endpoint")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/graphiql");
        registry.setApplicationDestinationPrefixes("/app");

    }

}
