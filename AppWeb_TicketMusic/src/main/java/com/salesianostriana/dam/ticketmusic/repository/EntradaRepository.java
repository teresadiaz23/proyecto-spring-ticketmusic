package com.salesianostriana.dam.ticketmusic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.ticketmusic.model.Entrada;
import com.salesianostriana.dam.ticketmusic.model.Evento;

public interface EntradaRepository extends JpaRepository<Entrada, Long>{
	
	@Query("select distinct e from Entrada e where TYPE(e) IN ('General', 'VIP', 'Premium') and e.evento.id = :id")
	List<Entrada> entradasPorTipo(@Param("id") Long id);
	
	@Query("select distinct e from Entrada e where e.nombre = :nombre and e.evento.id = :id and rownum = 1")
	Entrada entradaPorTipoByEventoId(@Param("nombre") String nombre, @Param("id") Long id);
	
	@Query("select distinct e from Entrada e where e.nombre = :nombre and e.evento.id = :id and rownum = 1")
	List<Entrada> entradasPorTiposByEventoId(@Param("nombre") String nombre, @Param("id") Long id);
	
	
	@Query("select e from Entrada e where e.nombre = :nombre and e.evento = :evento")
	List<Entrada> totalTipo(@Param("nombre") String nombre, @Param("evento") Evento evento);
	
	@Query("select distinct(e.precio) from Entrada e where e.nombre = :nombre and e.evento = :evento")
	double precioTipo(@Param("nombre") String nombre, @Param("evento") Evento evento);

}
