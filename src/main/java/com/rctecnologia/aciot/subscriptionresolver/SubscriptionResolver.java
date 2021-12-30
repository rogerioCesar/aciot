package com.rctecnologia.aciot.subscriptionresolver;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Predicate;


import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.rctecnologia.aciot.model.ContextModel;
import com.rctecnologia.aciot.model.Point;
import com.rctecnologia.aciot.model.Politica;
import com.rctecnologia.aciot.repository.PointRepository;
import com.rctecnologia.aciot.repository.PoliticaRepository;

import graphql.kickstart.tools.GraphQLSubscriptionResolver;


/**
 * Classe responsável por controlar (Resolver), via graphql, as ações de subscriptions no ambiente IoT
 * As ações de subscriptions proporcionam uma inscrição no tópico e publicações em tempo real
 * para os usuários inscritos no tópico, de acordo com as políticas cadastradas.
 * 
 * @author rogerio
 *
 */
@Component
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

	@Autowired
	PointRepository pointRepository;
	
	@Autowired
	PoliticaRepository politicaRepository;

	@Autowired
	public SubscriptionResolver(PointRepository pointRepository, PoliticaRepository politicaRepository) {
	}
	
	
	@PreAuthorize("isAuthenticated()")	
	public  Publisher<List<Point>> points(){
		return subscriber->	Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {		
			/* Preenchimento com políticas.
			*
			*
			Politica politica = new Politica(
						"p1", ">30", "5h", "0", "room1/bulb01","admin",
						"bedroom", ">18", "on", ">30%", "smartwatch"
						);
		
			List<Politica> politicas = new ArrayList<Politica>();
			
		    politicaRepository.findAll().forEach(politicas::add);
		    
		    politicas.forEach(politic -> System.out.println("id = "+politica.getId()));
		    politicas.forEach(politic -> System.out.println("name = "+politica.getName()));
		    politicas.forEach(politic -> System.out.println("temperatura = "+politica.getTemperatura()));
		    politicas.forEach(politic -> System.out.println("hora = "+politica.getHora()));
	        politicas.forEach(politic -> System.out.println("dia = "+politica.getDia()));  
	        politicas.forEach(politic -> System.out.println("point = "+politica.getPoint()));
	        politicas.forEach(politic -> System.out.println("role = "+politica.getRole()));
	        politicas.forEach(politic -> System.out.println("localizacao = "+politica.getLocalizacao()));
	        politicas.forEach(politic -> System.out.println("idade = "+politica.getIdade()));
	        politicas.forEach(politic -> System.out.println("conectividade = "+politica.getConectividade()));
	        politicas.forEach(politic -> System.out.println("bateria = "+politica.getBateria()));
	        politicas.forEach(politic -> System.out.println("dispositivo = "+politica.getDispositivo()));  
	        

			
		   /* try {
				
			    politicaRepository.save(new Politica( politica.getName(), politica.getTemperatura(), politica.getHora()
			    		,politica.getDia(), politica.getPoint(), politica.getRole(), politica.getLocalizacao(),
			    		politica.getIdade(), politica.getConectividade(), politica.getBateria(), politica.getDispositivo()));
			    
			  } catch (Exception e) {
			    System.out.print("Erro "+e);
			  }
			  
			  *
			  */
				
			List<Point> points = pointRepository.findAll();			
			
			List<Politica> politicas = politicaRepository.findAll();
			

			
			
			//método para buscar pontos.
			
			System.out.println("Printei "+pointsWithAllowedAccess().get(0).getPoint());
			
			
			//disponibliza dados sobre os pontos.
			subscriber.onNext(points);
			
			
			
		}, 0, 2, TimeUnit.SECONDS);				
	
	}
	

	//Método que retorna os pontos disponíveis de acordo com as políticas.
	
	public List<Politica> pointsWithAllowedAccess() {
		//
		Point p = new Point();
		
		List<Point> points = pointRepository.findAll();			
		
		List<Politica> politicas = politicaRepository.findAll();
		
		List<Politica> result = politicas.stream().map(temp -> {
            Politica obj = new Politica(null, null, null, null, null, null, null, null, null, null, null);
            obj.setId(temp.getId());
            obj.setBateria(temp.getBateria());
            obj.setConectividade(temp.getConectividade());
            obj.setDispositivo(temp.getDispositivo());
		    obj.setHora(temp.getHora());
		    obj.setIdade(temp.getIdade());
		    obj.setLocalizacao(temp.getLocalizacao());
		    obj.setPoint(temp.getPoint());
		    obj.setRole(temp.getRole());
		    obj.setTemperatura(temp.getTemperatura());
            obj.setDia(temp.getDia());          
            
            return obj;
        }).collect(Collectors.toList());
		
		ContextModel contextModel = new ContextModel(null, null, null, null, null, null, null, null, null, null, null);
		contextModel.setTemperatura(">30ss");
		contextModel.setConectividade("onw");
		
		
		System.out.println(contextModel.getTemperatura());
		politicas.forEach(System.out::println);
		
		 List<Politica> politicasDisponiveis;
		 
		 politicasDisponiveis = politicas.stream()
				 .filter(p1 -> p1
						 .getBateria()
						 .contains(contextModel.getTemperatura()) 		|| contextModel.getTemperatura() == null				  
						 
						 && p1
						 .getConectividade()
						 .contains(contextModel.getConectividade()) || contextModel.getConectividade() == null )

				 .collect(Collectors.toList());
		 
		 /*
myList.stream().filter(x -> x.size() > 10 && x -> x.isCool())
		    politicasDisponiveis = politicas.stream()
		        .filter( p1 -> {
		            return result.stream()
		                    .map(Politica::getBateria)
		                    //Compara se atende ao requisito da politica com relação a bateria
		                    .anyMatch(i2 -> i2.equals(p1.getBateria()))	;
		        }).collect(Collectors.toList());*/

		//politicasDisponiveis.forEach(pi -> System.out.println("Novo aqui: "+politicasDisponiveis.get(0).getConectividade()));
		
		
		
		
		return politicasDisponiveis;
	}
		
	
	
	
}
