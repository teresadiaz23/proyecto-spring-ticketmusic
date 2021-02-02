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

import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.model.Recinto;
import com.salesianostriana.dam.ticketmusic.service.CiudadServicio;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;
import com.salesianostriana.dam.ticketmusic.service.RecintoServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/recinto")
public class RecintoController {
	
	private final RecintoServicio recintoServicio;
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
	public String administrarRecintos(Model model) {
		model.addAttribute("recinto", new Recinto());
		model.addAttribute("recintos", recintoServicio.findAll());
		model.addAttribute("ciudades", ciudadServicio.findAll());
		
		return "administrador/recinto";
	}
	
	
	@PostMapping("/submit")
	public String submitRecinto(@ModelAttribute("recinto") Recinto recinto, Model model) {
		recintoServicio.save(recinto);
		model.addAttribute("ciudades", ciudadServicio.findAll());
		return "redirect:/admin/recinto/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarRecinto(@PathVariable("id") Long id, Model model) {
		Recinto recinto = recintoServicio.findById(id);
		model.addAttribute("ciudades", ciudadServicio.findAll());
		
		if(recinto != null) {
			model.addAttribute("recinto", recinto);
			return "administrador/recinto";
		}
		else {
			return "redirect:/admin/recinto/";
		}
		
	}
	
	@PostMapping("/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("recinto") Recinto recinto) {
		recintoServicio.edit(recinto);
		return "redirect:/admin/recinto/";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarRecinto(@PathVariable("id") Long id, Model model) {
		Recinto recinto = recintoServicio.findById(id);
		if(recinto != null) {
			recintoServicio.delete(recinto);
		}
		
		return "redirect:/admin/recinto/";
	}
	
	
	

}
