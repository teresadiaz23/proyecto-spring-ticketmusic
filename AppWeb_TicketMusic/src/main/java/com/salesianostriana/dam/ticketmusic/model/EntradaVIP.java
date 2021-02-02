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
@DiscriminatorValue("VIP")
public class EntradaVIP extends Entrada{
	
	public EntradaVIP(String nombre, double precio, Boolean vendida) {
		super(nombre, precio, vendida);
		
	}
	
	

}
