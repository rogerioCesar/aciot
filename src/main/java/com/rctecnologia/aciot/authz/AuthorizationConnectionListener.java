package com.rctecnologia.aciot.authz;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rctecnologia.aciot.model.Politica;
import com.rctecnologia.aciot.repository.PoliticaRepository;

import graphql.kickstart.execution.subscriptions.SubscriptionSession;
import graphql.kickstart.execution.subscriptions.apollo.ApolloSubscriptionConnectionListener;
import graphql.kickstart.execution.subscriptions.apollo.OperationMessage;

@Component
public class AuthorizationConnectionListener implements ApolloSubscriptionConnectionListener{
	
	@Autowired
	PoliticaRepository politicaRepository;
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthorizationConnectionListener.class);

	public void onConnect(SubscriptionSession session, OperationMessage message) {
	    log.debug("onConnect with payload {}", message.getPayload().getClass());
	    System.out.print("Conectou");
	    List<Politica> politicas = politicaRepository.findAll();
	    
        politicas.forEach(System.out::println);     // Printing all saved politicas;

	    
	  }
}
