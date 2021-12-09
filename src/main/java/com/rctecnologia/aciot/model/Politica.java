package com.rctecnologia.aciot.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;





@Document 
public class Politica {

	
	

	@Id
	private String id;
	
	private String name;
	
	private String temperatura;
	
	private String hora;
	
	private String dia;
	
	private String point;

	public Politica(String id, String name, String temperatura, String hora, String dia, String point) {
		super();
		this.id = id;
		this.name = name;
		this.temperatura = temperatura;
		this.hora = hora;
		this.dia = dia;
		this.point = point;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
	

	
}
