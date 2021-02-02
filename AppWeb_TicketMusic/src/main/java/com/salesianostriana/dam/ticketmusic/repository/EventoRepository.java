package com.salesianostriana.dam.ticketmusic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.ticketmusic.model.Evento;


public interface EventoRepository extends JpaRepository<Evento, Long>{
	
	@Query("select e from Evento e where TYPE(e) = 'C' and fecha >= CURRENT_DATE")
	List<Evento> listaConciertos();
	
	@Query("select e from Evento e where TYPE(e) = 'F' and fecha >= CURRENT_DATE")
	List<Evento> listaFertivales();
	
	@Query("select e from Evento e where rownum <=6")
	List<Evento> primeros6Eventos();
	
	@Query("select e from Evento e where fecha >= CURRENT_DATE order by e.fecha")
	List<Evento> ordenarPorFecha();
	
	List<Evento> findByNombreContainingIgnoreCase(String nombre);
	
	@Query("select e from Evento e left join fetch e.artistas a where lower(e.nombre) like %?1% "
			+ "or lower(e.recinto.ciudad.nombre) like %?1% or lower(e.recinto.nombre) like %?1%"
			+ " or lower(a.nombre) like %?1%")
	List<Evento> buscarEventos(String cadena);
	
	

	
	
	
	
	
	
	

}
