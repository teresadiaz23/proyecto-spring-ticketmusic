package com.salesianostriana.dam.ticketmusic.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.model.Usuario;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;
import com.salesianostriana.dam.ticketmusic.service.UsuarioServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/usuario")
public class UsuarioController {
	
	private final UsuarioServicio usuarioServicio;
	private final EventoServicio eventoServicio;
	
	@ModelAttribute("conciertos")
	public List<Evento> listaConciertos() {
		return eventoServicio.listarTodosConciertos();
		
	}
	
	@ModelAttribute("generos")
	public List<String> listaGeneros(){
		List<String> generos = Arrays.asList("Pop", "Rock", "Reggaeton", "Indie y Alternativo", "Latino");
		return generos;
	}

	
	@GetMapping("/")
	public String administrarUsuarios(Model model,  @RequestParam(name="q", required=false) String query) {
		List<Usuario> resultado = (query == null) ? usuarioServicio.findAll() : usuarioServicio.buscador(query);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarios", resultado);
		return "administrador/usuario";
	}
	
	
	
	@PostMapping("/submit")
	public String submitUsuario(@ModelAttribute("usuario") Usuario usuario, Model model,
			BCryptPasswordEncoder passwordEncoder) {
		
		usuarioServicio.save(usuario, passwordEncoder);
		return "redirect:/admin/usuario/";
	}
	
	
	
	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioServicio.findById(id);
		
		if(usuario != null) {
			model.addAttribute("usuario", usuario);
			return "administrador/usuario";
		}
		else {
			return "redirect:/admin/usuario/";
		}
		
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarUsuario(@PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioServicio.findById(id);
		if(usuario != null) {
			usuarioServicio.delete(usuario);
		}
		
		return "redirect:/admin/usuario/";
	}
	
	
	
	
	
	
	

}
