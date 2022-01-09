package com.rctecnologia.aciot.subscriptionresolver;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.rctecnologia.aciot.model.ContextModel;
import com.rctecnologia.aciot.model.Point;
import com.rctecnologia.aciot.model.Politica;
import com.rctecnologia.aciot.model.User;
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
	
	private final Map<String, User> users = new HashMap<>();
	
	@Autowired
	PoliticaRepository politicaRepository;

	@Autowired
	public SubscriptionResolver(PointRepository pointRepository, PoliticaRepository politicaRepository) {
	}
	
	public User validateToken(String token) {
	    return users.get(token);
	  }
	
	
	@PreAuthorize("isAuthenticated()")	
	public  Publisher<List<Point>> points(){
		return subscriber->	Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {		
			
			/* Preenchimento com política aleatória.
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
				
			long startTime = System.nanoTime();
			//método para buscar pontos acessíveis.			
			pointsWithAllowedAccess();			
			
			//disponibliza dados sobre os pontos à cada 2 segundos.
			subscriber.onNext(pointsWithAllowedAccess());			
			long endTime = System.nanoTime();
			
			long timeElapsed = endTime - startTime;
			 
	        System.out.println("Execution time in nanoseconds: " + timeElapsed);
	        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	        
	        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

	        System.out.println(String.format("Initial memory: %.2f KB", 

	          (double)memoryMXBean.getHeapMemoryUsage().getInit() /1024));

	        System.out.println(String.format("Used heap memory: %.2f KB", 

	          (double)memoryMXBean.getHeapMemoryUsage().getUsed() /1024));

	        System.out.println(String.format("Max heap memory: %.2f KB", 

	          (double)memoryMXBean.getHeapMemoryUsage().getMax() /1024));

	        System.out.println(String.format("Committed memory: %.2f KB", 

	          (double)memoryMXBean.getHeapMemoryUsage().getCommitted() /1024));
	        
		}, 0, 2, TimeUnit.SECONDS);				
	
	}
	


	/**
	 * Método que retorna os pontos disponíveis de acordo com as políticas.
	*/	
	public List<Point> pointsWithAllowedAccess() {
		
		// ponto auxiliar
		List<Point> p = new ArrayList<Point>();		
		
		//todas as políticas
		List<Politica> politicas = politicaRepository.findAll();
		
		/**
		 * MODELO USANDO JSON
		 * -Constrói json
		 * -Instancia contexto e pontos para acesso.
		 * -Verica pontos para acesso, de acordo com as políticas e contexto no momento.
		 */			
		
		//pontos acessíveis
		List<Point> pointsAccess =new ArrayList<Point>() ;	
		
		//modelo de contexto instanciado
		ContextModel contextModel = new ContextModel(null, null, null, null, null, null, null, null, null, null, null);
		contextModel.setTemperatura(">30");
		contextModel.setConectividade("on");
		
		int i=0;
		
		for(Politica polis : politicas) {		
			

			if(polis.getBateria() == contextModel.getBateria() 
					|| (polis.getBateria()==null || polis.getBateria().isEmpty()))  i++; 
			
			if(polis.getConectividade().equals(contextModel.getConectividade()) || (polis.getConectividade()==null 
					|| polis.getConectividade().isEmpty())) i++;
			
			if(polis.getDispositivo()== contextModel.getDispositivo() || polis.getDispositivo()==null) i++;
			if(polis.getHora()== contextModel.getHora() || polis.getHora()==null) i++;
			if(polis.getIdade()== contextModel.getIdade() || polis.getIdade()==null) i++;
			if(polis.getLocalizacao()== contextModel.getLocalização() || polis.getLocalizacao()==null) i++;
			if(polis.getPoint()== contextModel.getPoint() || polis.getPoint()==null) i++;
			if(polis.getRole()== contextModel.getRole() || polis.getRole()==null) i++;
			if(polis.getTemperatura()== contextModel.getTemperatura() || polis.getTemperatura()==null) i++;
			if(polis.getDia()== contextModel.getDia() || polis.getDia()==null) i++;
			
			if(i==9) {	

		        p = pointRepository.findByName(String.valueOf(polis.getPoint().toString()));      

		        for (Point ponto : p) {
					pointsAccess.add(ponto);
				}		        
			}
		}
					
		//retorna os pontos acessíveis.
		return pointsAccess;
		
	}
		
	
	
	
}
