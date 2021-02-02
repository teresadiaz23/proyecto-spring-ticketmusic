package com.salesianostriana.dam.ticketmusic.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class LineaVenta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int cantidadEnt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Entrada entrada;
	
	@ManyToOne
	private Venta venta;
	
	public LineaVenta(Entrada entrada) {
		super();
		this.entrada = entrada;
	}


	public LineaVenta(int cantidadEnt, Entrada entrada) {
		super();
		this.cantidadEnt = cantidadEnt;
		this.entrada = entrada;
	}

	public LineaVenta(int cantidadEnt, Venta venta) {
		super();
		this.cantidadEnt = cantidadEnt;
		this.venta = venta;
	}

	
	
	
	
	
	

}
