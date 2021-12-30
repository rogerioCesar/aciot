package com.rctecnologia.aciot.repository;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.client.model.geojson.Point;
import com.rctecnologia.aciot.model.Politica;

public interface PoliticaRepository extends MongoRepository<Politica, String>{
	
	List<Politica> findByPoint(String point);
	
	
	  
}
