package com.rctecnologia.aciot.subscriptionresolver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rctecnologia.aciot.model.Point;
import com.rctecnologia.aciot.model.Politica;
import com.rctecnologia.aciot.repository.PointRepository;
import com.rctecnologia.aciot.repository.PoliticaRepository;

import graphql.execution.reactive.SingleSubscriberPublisher.OnSubscriptionCallback;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;

@Component
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

	@Autowired
	PointRepository pointRepository;
	
	@Autowired
	PoliticaRepository politicaRepository;

	@Autowired
	public SubscriptionResolver(PointRepository pointRepository, PoliticaRepository politicaRepository) {
	}
	
	
	public  Publisher<List<Point>> points(){
		return subscriber->	Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
			
			List<Politica> politicas = politicaRepository.findAll();
			
			politicaRepository.saveAll(Arrays.asList(
			         new Politica("22","502", "nome", "temperatura", "dia", "point"),
			         new Politica("33","503", "nome2", "temperatura2", "di2a", "2point")
			         ));
			
			//if(politicas.isEmpty()) {System.out.println("políticas vazias");}
				
			List<Point> points = pointRepository.findAll();
			subscriber.onNext(points);
			
		}, 0, 2, TimeUnit.SECONDS);				
			}
	
	private void OnSubscriptionCallback() {
		
		// TODO Auto-generated method stub
System.out.println("Fiz inscrição");
	}
}
