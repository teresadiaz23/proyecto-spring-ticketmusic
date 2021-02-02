package com.salesianostriana.dam.ticketmusic.cesta;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.salesianostriana.dam.ticketmusic.model.Entrada;
import com.salesianostriana.dam.ticketmusic.service.EntradaServicio;

import lombok.RequiredArgsConstructor;

@Service
@SessionScope
@RequiredArgsConstructor
public class CestaImpl implements Cesta{
	
	private Map<Entrada, Integer> entradasEnCesta = new LinkedHashMap<>();
	private final EntradaServicio entradaServicio;
	
	
	

	@Override
	public void addToCesta(Entrada e) {
		int uno = 1;
		if(entradasEnCesta.containsKey(e)) {
			entradasEnCesta.put(e, entradasEnCesta.get(e)+uno);
		}
		else {
			entradasEnCesta.put(e, uno);
		}
		
		
	}

	@Override
	public void addToCesta(Long id) {
		Entrada entrada = entradaServicio.findById(id);
		if(entrada != null) {
			addToCesta(entrada);
		}
		
	}

	@Override
	public void removeFromCesta(Long id) {
		Entrada entrada = entradaServicio.findById(id);
		if(entrada != null) {
			removeFromCarrito(entrada);
		}
	}

	@Override
	public void removeFromCarrito(Entrada e) {
		entradasEnCesta.remove(e);
		
	}

	@Override
	public void clear() {
		entradasEnCesta.clear();
		
	}

	@Override
	public Map<Entrada, Integer> getCesta() {
		return Collections.unmodifiableMap(entradasEnCesta);
	}

	@Override
	public int numeroTotalProductosDiferentes() {
		return entradasEnCesta.size();
	}

	@Override
	public int numeroTotalDeUnidades() {
		int total = 0;
		for(Entrada e: entradasEnCesta.keySet()) {
			total += entradasEnCesta.get(e);
		}
		return total;

	}
	
	public double subTotal() {
		double total = 0.0;
		for(Entrada e: entradasEnCesta.keySet()) {
			total += entradasEnCesta.get(e) * e.getPrecio();
		}
		return total;
	}
	
	@Override
	public double importeTotal(double gastosGestion) {
		double total = 0.0;
	
		for(Entrada e: entradasEnCesta.keySet()) {
			total += entradasEnCesta.get(e) * e.calcularPrecio(gastosGestion);
		}
		return total;
	}

}
