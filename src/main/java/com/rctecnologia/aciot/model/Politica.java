package com.rctecnologia.aciot.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;



/**
 * Modelo de políticas com atributos do contexto a serem definidos para acesso.
 * As políticas são armazenadas em ambiente NoSQL -> MongoDB
 * @author rogerio
 *
 */
@Document 
public class Politica {
	@Id
	private String id;
	
    private String name;
	
	private String temperatura;
	
	private String hora;
	
	private String dia;
	
	private String point;
	
	private String role;
	
	private String localizacao;
	
	private String idade;
	
	private String conectividade;
	
	private String bateria;
	
	private String dispositivo;
	
	
	
	

	public Politica(String name, String temperatura, String hora, String dia, String point, String role,
			String localizacao, String idade, String conectividade, String bateria, String dispositivo) {
		this.name = name;
		this.temperatura = temperatura;
		this.hora = hora;
		this.dia = dia;
		this.point = point;
		this.role = role;
		this.localizacao = localizacao;
		this.idade = idade;
		this.conectividade = conectividade;
		this.bateria = bateria;
		this.dispositivo = dispositivo;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getConectividade() {
		return conectividade;
	}

	public void setConectividade(String conectividade) {
		this.conectividade = conectividade;
	}

	public String getBateria() {
		return bateria;
	}

	public void setBateria(String bateria) {
		this.bateria = bateria;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
	

	
}
