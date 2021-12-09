package com.rctecnologia.aciot.authz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketAuthenticationConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer{
	private static String[] authorities = new String[] {
			  "VIEW_SCRIPT_TAB", "VIEW_CREDS_TAB"
			};

	    private static final Logger logger = LoggerFactory.getLogger(WebSocketAuthenticationConfig.class);
	    
	    @Override
	    protected void configureInbound(MessageSecurityMetadataSourceRegistry message) {
	    
	      message
	        .nullDestMatcher().permitAll();
	        //.simpDestMatchers("/app/**").hasAnyAuthority(authorities)
	        //.simpSubscribeDestMatchers("/topic/ "+ "**").permitAll()
	        //.anyMessage().access(accessDecisionManager());	      
	     }
	    
	    @Override
	    protected void customizeClientInboundChannel(ChannelRegistration registration) {
	    	// TODO Auto-generated method stub
	    	registration.interceptors(new TopicSubscriptionInterception());
	    }
	    
	   /* @Bean
		public String accessDecisionManager() {
		    List<AccessDecisionVoter<? extends Object>> decisionVoters= (List) Arrays.asList( //* usei casting do List, pois aparentemente essa versão do java não consegue fazer essa conversão
		        new WebExpressionVoter(),
		        new RoleVoter(),
		        new AuthenticatedVoter());
		    return new AffirmativeBased(decisionVoters);
		}*/


}
