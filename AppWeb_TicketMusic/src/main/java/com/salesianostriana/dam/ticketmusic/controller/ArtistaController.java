package com.salesianostriana.dam.ticketmusic.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.ticketmusic.model.Artista;
import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.service.ArtistaServicio;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/artista")
public class ArtistaController {
	
	private final ArtistaServicio artistaServicio;
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
	public String administrarArtistas(Model model, @RequestParam(name="q", required=false) String query) {
		List<Artista> resultado = (query == null) ? artistaServicio.findAll() : artistaServicio.buscador(query);
		model.addAttribute("artista", new Artista());
		model.addAttribute("artistas", resultado);
		return "administrador/artista";
	}
	
	
	
	@PostMapping("/submit")
	public String submitArtista(@ModelAttribute("artista") Artista artista, Model model) {
		artistaServicio.save(artista);
		return "redirect:/admin/artista/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarArtista(@PathVariable("id") Long id, Model model) {
		Artista artista = artistaServicio.findById(id);
		
		if(artista != null) {
			model.addAttribute("artista", artista);
			return "administrador/artista";
		}
		else {
			return "redirect:/admin/artista/";
		}
		
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarArtista(@PathVariable("id") Long id, Model model) {
		Artista artista = artistaServicio.findById(id);
		if(artista != null) {
			artistaServicio.delete(artista);
		}
		
		return "redirect:/admin/artista/";
	}
	
	
	

}
