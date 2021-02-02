package com.salesianostriana.dam.ticketmusic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Recinto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private int aforo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Ciudad ciudad;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "recinto", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Evento> eventos = new ArrayList<>();
	
	public Recinto(String nombre, int aforo) {
		super();
		this.nombre = nombre;
		this.aforo = aforo;
	}
	
	public Recinto(String nombre, int aforo, Ciudad ciudad) {
		super();
		this.nombre = nombre;
		this.aforo = aforo;
		this.ciudad = ciudad;
	}
	
	public Recinto(String nombre, int aforo, List<Evento> eventos) {
		super();
		this.nombre = nombre;
		this.aforo = aforo;
		this.eventos = eventos;
	}

	
	public Recinto(String nombre, int aforo, Ciudad ciudad, List<Evento> eventos) {
		super();
		this.nombre = nombre;
		this.aforo = aforo;
		this.ciudad = ciudad;
		this.eventos = eventos;
	}
	
	
	//Métodos helpers
	public void addEvento(Evento e) {
		this.eventos.add(e);
		e.setRecinto(this);
	}
	
	public void removeEvento(Evento e) {
		this.eventos.remove(e);
		e.setRecinto(null);
	}
	
	



	



	


	
	
	

}
