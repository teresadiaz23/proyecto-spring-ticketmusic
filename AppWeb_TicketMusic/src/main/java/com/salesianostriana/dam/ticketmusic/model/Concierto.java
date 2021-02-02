package com.salesianostriana.dam.ticketmusic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("C")
public class Concierto extends Evento{
	
	private String genero;
	
	public Concierto(String nombre, LocalDate fecha, LocalTime hora, String genero) {
		super(nombre, fecha, hora);
		this.genero = genero;
	}

	public Concierto(String nombre, LocalDate fecha, LocalTime hora, String cartel, String genero) {
		super(nombre, fecha, hora, cartel);
		this.genero = genero;
	}

	@Override
	public String getTipo() {
		
		return "Concierto";
	}

	@Override
	public String sacarGenero() {
		
		return genero;
	}
	
	
	
	



	
	
	
	
	

}
