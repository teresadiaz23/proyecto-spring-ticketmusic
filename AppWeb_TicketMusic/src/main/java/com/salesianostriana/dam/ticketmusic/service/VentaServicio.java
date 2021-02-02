package com.salesianostriana.dam.ticketmusic.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.ticketmusic.cesta.Cesta;
import com.salesianostriana.dam.ticketmusic.model.Entrada;
import com.salesianostriana.dam.ticketmusic.model.LineaVenta;
import com.salesianostriana.dam.ticketmusic.model.Usuario;
import com.salesianostriana.dam.ticketmusic.model.Venta;
import com.salesianostriana.dam.ticketmusic.repository.VentaRepository;
import com.salesianostriana.dam.ticketmusic.service.base.BaseService;


@Service

public class VentaServicio extends BaseService<Venta, Long, VentaRepository>{
	

	
	private final EntradaServicio entradaServicio;
	
	
	
	public VentaServicio(VentaRepository repo, EntradaServicio entradaServicio) {
		super(repo);
		this.entradaServicio = entradaServicio;
		
	}


	public void realizarCompra(Cesta cesta, Usuario usuario, double gastosGestion, Venta venta) {
		
		for(Entrada e : cesta.getCesta().keySet()) {
			venta.addLineaVenta(new LineaVenta(e));
			e.setVendida(true);
			entradaServicio.edit(e);
		}
		
		repositorio.save(venta);
		usuario.addVenta(venta);
		
	}
	
	public double calcularRecaudacion() {
		double total = 0.0;
		for(Venta v: repositorio.findAll()) {
			total += v.getPrecioTotal();
		}
		return total;
	}
	
	


	

}
