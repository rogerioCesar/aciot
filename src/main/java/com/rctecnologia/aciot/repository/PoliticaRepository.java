package com.rctecnologia.aciot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rctecnologia.aciot.model.Politica;

public interface PoliticaRepository extends MongoRepository<Politica, String>{
	  
}
