package com.salesianostriana.dam.ticketmusic.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.ticketmusic.model.LineaVenta;
import com.salesianostriana.dam.ticketmusic.repository.LineaVentaRepository;
import com.salesianostriana.dam.ticketmusic.service.base.BaseService;

@Service
public class LineaVentaServicio extends BaseService<LineaVenta, Long, LineaVentaRepository>{

	public LineaVentaServicio(LineaVentaRepository repo) {
		super(repo);
		
	}

	

}
