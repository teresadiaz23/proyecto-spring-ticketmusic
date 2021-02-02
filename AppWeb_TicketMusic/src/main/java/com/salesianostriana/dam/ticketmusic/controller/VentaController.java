package com.salesianostriana.dam.ticketmusic.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;
import com.salesianostriana.dam.ticketmusic.service.VentaServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/venta")
public class VentaController {
	
	private final VentaServicio ventaServicio;
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
	public String administrarVentas(Model model) {
		
		model.addAttribute("ventas", ventaServicio.findAll());
		model.addAttribute("recaudacion", ventaServicio.calcularRecaudacion());
		return "administrador/venta";
	}
	



}
