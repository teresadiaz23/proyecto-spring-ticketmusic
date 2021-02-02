package com.salesianostriana.dam.ticketmusic.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.ticketmusic.model.Artista;
import com.salesianostriana.dam.ticketmusic.repository.ArtistaRepository;
import com.salesianostriana.dam.ticketmusic.service.base.BaseService;

@Service
public class ArtistaServicio extends BaseService<Artista, Long, ArtistaRepository>{

	public ArtistaServicio(ArtistaRepository repo) {
		super(repo);
	}
	
	public List<Artista> buscador(String nombre){
		return repositorio.findByNombreContainingIgnoreCase(nombre);
	}
	
	

}
