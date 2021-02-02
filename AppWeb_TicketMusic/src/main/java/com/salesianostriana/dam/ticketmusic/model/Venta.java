package com.salesianostriana.dam.ticketmusic.model;

import java.time.LocalDate;
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
public class Venta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private double precioTotal;
	private LocalDate fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<LineaVenta> lineasVenta = new ArrayList<>();
	

	public Venta(double precioTotal) {
		super();
		this.precioTotal = precioTotal;
		this.fecha = LocalDate.now();
	}


	public Venta(double precioTotal, Usuario usuario) {
		super();
		this.precioTotal = precioTotal;
		this.usuario = usuario;
	}
	
	public Venta(double precioTotal, List<LineaVenta> lineasVenta) {
		super();
		this.precioTotal = precioTotal;
		this.lineasVenta = lineasVenta;
	}
	
	//Métodos helpers LineaCesta
	public void addLineaVenta(LineaVenta l) {
		l.setVenta(this);
		this.lineasVenta.add(l);
	}
	public void removeLineaVenta(LineaVenta l) {
		this.lineasVenta.remove(l);
		l.setVenta(null);
	}

	
	
	
	
	
	
	

}
