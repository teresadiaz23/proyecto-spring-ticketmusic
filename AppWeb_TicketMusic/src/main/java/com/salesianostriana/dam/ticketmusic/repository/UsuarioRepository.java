package com.salesianostriana.dam.ticketmusic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.ticketmusic.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findFirstByEmail(String email);
	
	@Query("select u from Usuario u where lower(u.nombre) like %?1% or lower(u.apellidos) like %?1% or lower(u.email) like %?1% ")
	List<Usuario> encuentraPorNombreApellidoOEmail(String cadena);
	
	

}
