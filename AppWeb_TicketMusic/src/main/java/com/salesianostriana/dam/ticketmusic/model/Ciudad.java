package com.salesianostriana.dam.ticketmusic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ciudad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Recinto> recintos = new ArrayList<>();

	public Ciudad(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	public Ciudad(String nombre, List<Recinto> recintos) {
		super();
		this.nombre = nombre;
		this.recintos = recintos;
	}
	
	
	//Métodos helpers
	
	public void addRecinto(Recinto r) {
		this.recintos.add(r);
		r.setCiudad(this);
	}
	
	public void removeRecinto(Recinto r) {
		this.recintos.remove(r);
		r.setCiudad(null);
	}
	


	
	
	

}
