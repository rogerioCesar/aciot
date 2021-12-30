package com.rctecnologia.aciot.authz;

import java.util.ArrayList;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.websocket.server.HandshakeRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rctecnologia.aciot.model.Politica;
import com.rctecnologia.aciot.repository.PoliticaRepository;

import graphql.kickstart.execution.subscriptions.SubscriptionSession;
import graphql.kickstart.execution.subscriptions.apollo.ApolloSubscriptionConnectionListener;
import graphql.kickstart.execution.subscriptions.apollo.OperationMessage;

/*
 * Algoritmo
 * Recebe topico e Id dos usuparios
 * Recupera atributos atributos
 * monta contexto
 * Verifica no banco as políticas
 * retorna sim ou não.
 */
@Component
public class Authz implements ApolloSubscriptionConnectionListener {
	
	@Autowired
	PoliticaRepository politicaRepository;
	
	@Autowired
	private Authenticator authenticator;
	


	@Override
	  public void onConnect(SubscriptionSession session, OperationMessage message) {
		
		Map<String, String> payload = (Map<String, String>) message.getPayload();
	    System.out.println("Payload: "+payload);
	    if (payload != null) {
	      // Authenticate using the value of the key Authorization of the payload
	      authenticator.doAuthenticate(payload.get(HttpHeaders.AUTHORIZATION)); 
	    }
	    
	   
	  }

	  @Override	
	  public void onStart(SubscriptionSession session, OperationMessage message) {
		  
		  /*
		    List<Politica> politicas = new ArrayList<Politica>();
			
		    politicaRepository.findAll().forEach(politicas::add);
		    
		    politicas.forEach(politic -> System.out.println("id = "+politic.getId()));
		    politicas.forEach(politic -> System.out.println("name = "+politic.getName()));
		    politicas.forEach(politic -> System.out.println("temperatura = "+politic.getTemperatura()));
		    politicas.forEach(politic -> System.out.println("hora = "+politic.getHora()));
	        politicas.forEach(politic -> System.out.println("dia = "+politic.getDia()));  
	        politicas.forEach(politic -> System.out.println("point = "+politic.getPoint()));
	        politicas.forEach(politic -> System.out.println("role = "+politic.getRole()));
	        politicas.forEach(politic -> System.out.println("localizacao = "+politic.getLocalizacao()));
	        politicas.forEach(politic -> System.out.println("idade = "+politic.getIdade()));
	        politicas.forEach(politic -> System.out.println("conectividade = "+politic.getConectividade()));
	        politicas.forEach(politic -> System.out.println("bateria = "+politic.getBateria()));
	        politicas.forEach(politic -> System.out.println("dispositivo = "+politic.getDispositivo()));  
	   
			*/
	      
	      // Retrieve the handshake request from the current subscription session
	      HandshakeRequest request = (HandshakeRequest) session.getUserProperties()
	        .get(HandshakeRequest.class.getName());
	      
	      System.out.println(session.getUserProperties().get(HandshakeRequest.class.getName()));
	      
	      
	      
	      /* Check for an Authorization header in the handshake request and use it to authenticate */
	      Optional.ofNullable(request)
	          .map(HandshakeRequest::getHeaders)
	          .map(headers -> headers.get(HttpHeaders.AUTHORIZATION))
	          .filter(list -> !list.isEmpty())
	          .map(list -> list.get(0))
	          .ifPresent(authHeaderValue -> authenticator.doAuthenticate(authHeaderValue));
	    }


	
}
