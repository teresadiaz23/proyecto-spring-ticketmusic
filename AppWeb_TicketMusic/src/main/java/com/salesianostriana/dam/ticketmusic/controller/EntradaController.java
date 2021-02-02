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

import com.salesianostriana.dam.ticketmusic.model.Entrada;
import com.salesianostriana.dam.ticketmusic.model.EntradaGeneral;
import com.salesianostriana.dam.ticketmusic.model.EntradaPremium;
import com.salesianostriana.dam.ticketmusic.model.EntradaVIP;
import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.service.EntradaServicio;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/entrada")
public class EntradaController {
	
	private final EntradaServicio entradaServicio;
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
	public String administrarEntradas(Model model,
			@RequestParam(name = "id", required = false, defaultValue = "1") Long id,
			@RequestParam(name = "precio", required = false, defaultValue = "1.0") Double precio,
			Evento evento) {
		
		
		model.addAttribute("entrada", new Entrada());
		model.addAttribute("entradas", entradaServicio.findAll());
		model.addAttribute("id", id);
		model.addAttribute("precio", precio);
		model.addAttribute("eventos", eventoServicio.findAll());
		model.addAttribute("entradaServicio", entradaServicio);
		model.addAttribute("totales", eventoServicio.findAll().get(0).getEntradas().size());
		return "administrador/entrada";
	}
	
	
	@PostMapping("/submit")
	public String submitEntrada(@ModelAttribute("entrada") Entrada entrada, Model model,
			@ModelAttribute("precio") Double precio, @ModelAttribute("id") Long id) {
		
		Evento evento = eventoServicio.findById(id);
		model.addAttribute("entrada", entrada);
		
		
		model.addAttribute("eventos", eventoServicio.findAll());
		
		return "redirect:/admin/entrada/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarEntrada(@PathVariable("id") Long id, Model model,
			@RequestParam(name = "precio1", required = false, defaultValue = "1.0") Double precio1,
			@RequestParam(name = "precio2", required = false, defaultValue = "1.0") Double precio2,
			@RequestParam(name = "precio3", required = false, defaultValue = "1.0") Double precio3) {
		
		Evento evento = eventoServicio.findById(id);
		model.addAttribute("eventos", eventoServicio.findAll());
		
		if(evento != null) {
			model.addAttribute("evento", evento);
			model.addAttribute("precio1", precio1);
			model.addAttribute("precio2", precio2);
			model.addAttribute("precio3", precio3);
			
			
			return "administrador/entradaForm";
		}
		else {
			return "redirect:/admin/entrada/";
		}

		
	}
	
	@PostMapping("/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("evento") Evento evento,
			EntradaGeneral general, EntradaVIP vip, EntradaPremium premium,
			@ModelAttribute("precio1") Double precio1,  @ModelAttribute("precio2") Double precio2,
			 @ModelAttribute("precio3") Double precio3) {
		
		
		entradaServicio.editarPrecios(evento, precio1, precio2, precio3);
		
		return "redirect:/admin/entrada/";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarEntrada(@PathVariable("id") Long id, Model model) {
		Entrada entrada = entradaServicio.findById(id);
		if(entrada != null) {
			entradaServicio.delete(entrada);
		}
		
		return "redirect:/admin/entrada/";
	}

}
