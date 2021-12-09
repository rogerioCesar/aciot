package com.rctecnologia.aciot.authz;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

import com.rctecnologia.aciot.model.Politica;
import com.rctecnologia.aciot.model.User;
import com.rctecnologia.aciot.repository.PointRepository;
import com.rctecnologia.aciot.repository.PoliticaRepository;


public class TopicSubscriptionInterception implements ChannelInterceptor{
	
	@Autowired
	PointRepository pointRepository;
	
	@Autowired
	PoliticaRepository politicaRepository;

	
	 private static Logger logger = org.slf4j.LoggerFactory.getLogger(TopicSubscriptionInterception.class);

	    @Override
	    public Message<?> postReceive(Message<?> message, MessageChannel chanenel) {
	    	System.out.println("Olápolpolç");
	        return message;
	        
	    }

	    @Override
	    public void postSend(Message<?> message, MessageChannel chanel, boolean sent) {
	    	System.out.println("Olápolpolç");
	    }

	    @Override
	    public boolean preReceive(MessageChannel channel) {
	        return true;
	        
	    }

	    @Override
	    public Message<?> preSend(Message<?> message, MessageChannel channel) {
	       
	        return message;
	    }

	    private boolean validateSubscription(User principal, String topicDestination)
	    {
	        logger.debug("Validate subscription for {} to topic {}","Usuario - ",topicDestination);
	        
	        List<Politica> politicas = politicaRepository.findAll();
			
			if(politicas.isEmpty()) {System.out.println("políticas vazias");
			System.out.println("Validate subscription for {} to topic  "+"Usuario - "+topicDestination);
			return false;

			}
	        //Validation logic coming here
	        return false;
	    }

}
