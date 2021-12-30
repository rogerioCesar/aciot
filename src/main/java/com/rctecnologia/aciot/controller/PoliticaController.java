package com.rctecnologia.aciot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rctecnologia.aciot.model.Politica;

import java.util.ArrayList;
import java.util.List;
import com.rctecnologia.aciot.repository.PoliticaRepository;
/*
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")*/
public class PoliticaController {
	/*
	@Autowired
	PoliticaRepository politicaRepository;
	
	@GetMapping("/politica")
	public ResponseEntity<List<Politica>> getAllPolitics(@RequestParam(required =  false) String point){
		System.out.print("olá");
		try {
		    List<Politica> politicas = new ArrayList<Politica>();

		    if (point == null)
		      politicaRepository.findAll().forEach(politicas::add);
		    else 	
		      politicaRepository.findByPoint(point).forEach(politicas::add);

		    if (politicas.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(politicas, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }		
	}
	
	  @PostMapping("/politicas")
	  public ResponseEntity<Politica> createTutorial(@RequestBody Politica politica) {
		  System.out.print("Olá");
		  try {
			    Politica _politica = politicaRepository.save(new Politica( politica.getName(), politica.getTemperatura(), politica.getHora()
			    		,politica.getDia(), politica.getPoint()));
			    return new ResponseEntity<>(_politica, HttpStatus.CREATED);
			  } catch (Exception e) {
			    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			  }	    
	  }
	
	
*/
}
