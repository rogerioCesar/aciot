package com.rctecnologia.aciot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Modelo de ponto para controle de elemento/entidade/recurso/ no ambiente IoT
 * @author rogerio
 *
 */

@Entity
@Table
public class Point {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	 
	  @Column(name = "typepoint")
	  private String typepoint;
	  
	  @Column(name = "value")
	  private String value;

	  @Column(name = "name")
	  private String name;	  
	  
	  public Point () {		  
	  }
	  
	  public Point (Long id) {
		  this.id = id;
	  }	  
	  
	  public Point(String typepoint, String value, String name) {
			this.typepoint = typepoint;
			this.value = value;
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTypepoint() {
			return typepoint;
		}

		public void setTypepoint(String typepoint) {
			this.typepoint = typepoint;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		 @Override
		  public String toString() {
		    return "Point [id= " + id + ", name= " + name + ", value= " + value + ", typepoint= "+ typepoint +" ]";
		  }

}