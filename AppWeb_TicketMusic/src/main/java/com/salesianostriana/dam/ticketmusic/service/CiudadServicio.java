package com.salesianostriana.dam.ticketmusic.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.ticketmusic.model.Ciudad;
import com.salesianostriana.dam.ticketmusic.repository.CiudadRepository;
import com.salesianostriana.dam.ticketmusic.service.base.BaseService;

@Service
public class CiudadServicio extends BaseService<Ciudad, Long, CiudadRepository>{

	public CiudadServicio(CiudadRepository repo) {
		super(repo);
		
	}

}
