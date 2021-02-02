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
@DiscriminatorValue("General")
public class EntradaGeneral extends Entrada{
	
	public EntradaGeneral(String nombre, double precio, Boolean vendida) {
		super(nombre, precio, vendida);
		
	}
	

	
	

}
