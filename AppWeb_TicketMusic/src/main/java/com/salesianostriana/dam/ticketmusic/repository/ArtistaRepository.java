package com.salesianostriana.dam.ticketmusic.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.ticketmusic.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long>{
	
	List<Artista> findByNombreContainingIgnoreCase(String nombre);
	

}
