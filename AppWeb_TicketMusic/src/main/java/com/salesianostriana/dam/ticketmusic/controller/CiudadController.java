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

import com.salesianostriana.dam.ticketmusic.model.Ciudad;
import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.service.CiudadServicio;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/ciudad")
public class CiudadController {
	
	private final CiudadServicio ciudadServicio;
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
	public String administrarCiudad(Model model) {
		model.addAttribute("ciudad", new Ciudad());
		model.addAttribute("ciudades", ciudadServicio.findAll());
		
		return "administrador/ciudad";
	}
	
	
	@PostMapping("/submit")
	public String submitCiudad(@ModelAttribute("ciudad") Ciudad ciudad, Model model) {
		ciudadServicio.save(ciudad);
		return "redirect:/admin/ciudad/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCiudad(@PathVariable("id") Long id, Model model) {
		Ciudad ciudad = ciudadServicio.findById(id);
		
		if(ciudad != null) {
			model.addAttribute("ciudad", ciudad);
			return "administrador/ciudad";
		}
		else {
			return "redirect:/admin/ciudad/";
		}
		
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarCiudad(@PathVariable("id") Long id, Model model) {
		Ciudad ciudad = ciudadServicio.findById(id);
		if(ciudad != null) {
			ciudadServicio.delete(ciudad);
		}
		
		return "redirect:/admin/ciudad/";
	}
	
	

}
