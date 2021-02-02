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
@DiscriminatorValue("F")
public class Festival extends Evento{
	
	
	public Festival(String nombre, LocalDate fecha, LocalTime hora) {
		super(nombre, fecha, hora);
		
	}

	public Festival(String nombre, LocalDate fecha, LocalTime hora, String cartel) {
		
		super(nombre, fecha, hora, cartel);
	}
	
	@Override
	public String getTipo() {
		
		return "Festival";
	}
	
	
	
	
	

}
