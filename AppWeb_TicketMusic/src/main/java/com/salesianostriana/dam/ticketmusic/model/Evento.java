package com.salesianostriana.dam.ticketmusic.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Evento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	private LocalTime hora;
	private String cartel;

	
	@ManyToOne(fetch = FetchType.EAGER)
	private Recinto recinto;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(
			joinColumns = @JoinColumn(name="evento_id"),
			inverseJoinColumns = @JoinColumn(name="artista_id")
		)
	private List<Artista> artistas = new ArrayList<>();
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "evento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Entrada> entradas = new ArrayList<>();
	
	public Evento(String nombre, LocalDate fecha, LocalTime hora) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public Evento(String nombre, LocalDate fecha, LocalTime hora, String cartel) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.cartel = cartel;
	}
	
	public Evento(String nombre, LocalDate fecha, LocalTime hora, String cartel, Recinto recinto) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.cartel = cartel;
		this.recinto = recinto;
	}


	
	//Métodos helpers Artistas
	public void addArtista(Artista a) {
		artistas.add(a);
		a.getEventos().add(this);
	}
	
	public void removeArtista(Artista a) {
		artistas.remove(a);
		a.getEventos().remove(this);
	}
	
	
	//Métodos helpers Entradas
	public void addEntrada(Entrada e) {
		this.entradas.add(e);
		e.setEvento(this);
	}
	
	public void removeEntrada(Entrada e) {
		this.entradas.remove(e);
		e.setEvento(null);
	}
	
	public String getTipo() {
		return "Evento";
	}
	
	public String sacarGenero() {
		return "";
	}

	

	

	
	
	
	



	
	
	
	
	

}
