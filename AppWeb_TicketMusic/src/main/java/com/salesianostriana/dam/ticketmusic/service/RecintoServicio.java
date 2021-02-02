package com.salesianostriana.dam.ticketmusic.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.ticketmusic.model.Recinto;
import com.salesianostriana.dam.ticketmusic.repository.RecintoRepository;
import com.salesianostriana.dam.ticketmusic.service.base.BaseService;

@Service
public class RecintoServicio extends BaseService<Recinto, Long, RecintoRepository>{

	public RecintoServicio(RecintoRepository repo) {
		super(repo);
		
	}

}
