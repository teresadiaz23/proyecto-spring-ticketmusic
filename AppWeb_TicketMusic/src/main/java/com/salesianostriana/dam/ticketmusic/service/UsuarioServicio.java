package com.salesianostriana.dam.ticketmusic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.ticketmusic.model.Usuario;
import com.salesianostriana.dam.ticketmusic.repository.UsuarioRepository;
import com.salesianostriana.dam.ticketmusic.service.base.BaseService;

@Service
public class UsuarioServicio extends BaseService<Usuario, Long, UsuarioRepository>{

	public UsuarioServicio(UsuarioRepository repo) {
		super(repo);
		
	}
	
	public Optional<Usuario> buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}
	
	public Usuario save(Usuario u, BCryptPasswordEncoder passwordEncoder) {
		
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return super.save(u);
	}
	
	public List<Usuario> buscador(String cadena){
		return repositorio.encuentraPorNombreApellidoOEmail(cadena.toLowerCase());
	}
}
