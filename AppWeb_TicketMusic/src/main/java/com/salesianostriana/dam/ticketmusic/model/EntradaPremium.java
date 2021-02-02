package com.salesianostriana.dam.ticketmusic.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@DiscriminatorValue("Premium")
public class EntradaPremium extends Entrada{
	
	public EntradaPremium(String nombre, double precio, Boolean vendida) {
		super(nombre, precio, vendida);
		
	}
	
	
	
	

}
