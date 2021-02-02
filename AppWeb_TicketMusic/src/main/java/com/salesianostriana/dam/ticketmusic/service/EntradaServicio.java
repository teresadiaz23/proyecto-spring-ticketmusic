package com.salesianostriana.dam.ticketmusic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.ticketmusic.model.Entrada;
import com.salesianostriana.dam.ticketmusic.model.EntradaGeneral;
import com.salesianostriana.dam.ticketmusic.model.EntradaPremium;
import com.salesianostriana.dam.ticketmusic.model.EntradaVIP;
import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.repository.EntradaRepository;
import com.salesianostriana.dam.ticketmusic.service.base.BaseService;

@Service
public class EntradaServicio extends BaseService<Entrada, Long, EntradaRepository>{

	public EntradaServicio(EntradaRepository repo) {
		super(repo);
		
	}
	
	public List<Entrada> crearEntradas(int cantidad, double precio){
		List<Entrada> entradas = new ArrayList<>();
		
		for (int i = 0; i < cantidad; i++) {
			
				
				if(i < cantidad*1/3) {
					entradas.add(new EntradaGeneral("Entrada General", precio, false));
				}
				else if(i >= cantidad*1/3 && i < cantidad*2/3 ) {
					entradas.add(new EntradaVIP("Entrada VIP", precio*(1+0.5), false));
				}
				else {
					entradas.add(new EntradaPremium("Entrada Premium", precio*2, false));
					
				}

		}
		return entradas;
			
	}
	

	
	public List<Entrada> crearEntradas2(Evento evento, double precio1, double precio2, double precio3){
		List<Entrada> entradas = new ArrayList<>();
		int cantidad = evento.getRecinto().getAforo();
		for (int i = 0; i < cantidad; i++) {
			
				
				if(i < cantidad*2/3) {
					entradas.add(new EntradaGeneral("Entrada General", precio1, false));
				}
				else if(i >= cantidad*2/3 && i < cantidad*2.5/3 ) {
					entradas.add(new EntradaVIP("Entrada VIP", precio2, false));
				}
				else {
					entradas.add(new EntradaPremium("Entrada Premium", precio3, false));
					
				}

		}
		return entradas;
			
	}
	public List<Entrada> entradasPorTipo(Long id){
		return repositorio.entradasPorTipo(id);
	}
	
	public Entrada entradaTipoEventoId(String nombre, Long id) {
		return repositorio.entradaPorTipoByEventoId(nombre, id);
		
	}
	
	public List<Entrada> entradasDisponibles(Evento evento){
		List<Entrada> cantidades = evento.getEntradas();
		return cantidades.stream()
				.filter(e->e.getVendida()==false)
				.collect(Collectors.toList());
	}
	public List<Entrada> disponiblesGeneral(Evento evento){
		List<Entrada> cantidades = evento.getEntradas();
		return cantidades.stream()
				.filter(e->e.getNombre().equalsIgnoreCase("Entrada General"))
				.filter(e->e.getVendida()==false)
				.collect(Collectors.toList());
	}
	
	
	public List<Entrada> disponiblesVip(Evento evento){
		List<Entrada> cantidades = evento.getEntradas();
		return cantidades.stream()
				.filter(e->e.getNombre().equalsIgnoreCase("Entrada VIP"))
				.filter(e->e.getVendida()==false)
				.collect(Collectors.toList());
	}
	public List<Entrada> disponiblesPremium(Evento evento){
		List<Entrada> cantidades = evento.getEntradas();
		return cantidades.stream()
				.filter(e->e.getNombre().equalsIgnoreCase("Entrada Premium"))
				.filter(e->e.getVendida()==false)
				.collect(Collectors.toList());
	}
	
	public List<Entrada> totalTipo(String nombre, Evento evento){
		return repositorio.totalTipo(nombre, evento);
	}
	
	public double precioTipo(String nombre, Evento evento) {
		return repositorio.precioTipo(nombre, evento);
	}
	
	
	public List<Integer> cantidades(int cant1, int cant2, int cant3){
		List<Integer> cantidades = Arrays.asList(cant1, cant2, cant3);
		return cantidades;
	}

	public List<Entrada> comprarEntradas(List<Integer> cantidades, Evento evento){
		List<Entrada> entradas = new ArrayList<>();
		if(cantidades.get(0) > 0) {
			
			for (int i = 0; i < cantidades.get(0); i++) {
				entradas.add(entradasDisponibles(evento)
						.stream()
						.filter(e -> e.getNombre().equalsIgnoreCase("Entrada General"))
						.collect(Collectors.toList())
						.get(i));
			}
			
		}
		if(cantidades.get(1) > 0) {
			for (int i = 0; i < cantidades.get(1); i++) {
				entradas.add(entradasDisponibles(evento)
						.stream()
						.filter(e -> e.getNombre().equalsIgnoreCase("Entrada VIP"))
						.collect(Collectors.toList())
						.get(i));
			}
			
		}
		if(cantidades.get(2) > 0) {
			for (int i = 0; i < cantidades.get(2); i++) {
				entradas.add(entradasDisponibles(evento)
						.stream()
						.filter(e -> e.getNombre().equalsIgnoreCase("Entrada Premium"))
						.collect(Collectors.toList())
						.get(i));
			}
			
		}
		return entradas;
		
	}
	
	public void editarPrecios(Evento evento, double precio1, double precio2, double precio3) {
		List<Entrada> entradas = evento.getEntradas();
		for(Entrada e: entradas) {
			if(e instanceof EntradaGeneral) {
				e.setPrecio(precio1);
			}
			if(e instanceof EntradaVIP) {
				e.setPrecio(precio2);
			}
			if(e instanceof EntradaPremium) {
				e.setPrecio(precio3);
			}
			repositorio.save(e);
		}
	}
		
		
	
}
