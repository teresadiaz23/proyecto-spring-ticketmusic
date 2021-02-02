package com.salesianostriana.dam.ticketmusic.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data @NoArgsConstructor @AllArgsConstructor
public class Entrada {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private double precio;
	private Boolean vendida;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Evento evento;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "entrada", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LineaVenta> lineaVenta = new ArrayList<>();
	
	
	public Entrada(String nombre, double precio, Boolean vendida) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.vendida = vendida;
	}
	
	
		
	//Métodos propios
	public double calcularPrecio(double gastosGestion) {
		return precio + gastosGestion;
	}
	

}
