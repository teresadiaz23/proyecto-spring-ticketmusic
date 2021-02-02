package com.salesianostriana.dam.ticketmusic.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.ticketmusic.cesta.Cesta;
import com.salesianostriana.dam.ticketmusic.formbeans.SearchBean;
import com.salesianostriana.dam.ticketmusic.model.Entrada;
import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.service.EntradaServicio;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CompraEntradaController {
	
	private final EntradaServicio entradaServicio;
	private final EventoServicio eventoServicio;
	private final Cesta cesta;
	
	@ModelAttribute("conciertos")
	public List<Evento> listaConciertos() {
		return eventoServicio.listarTodosConciertos();
		
	}
	
	@ModelAttribute("generos")
	public List<String> listaGeneros(){
		List<String> generos = Arrays.asList("Pop", "Rock", "Reggaeton", "Indie y Alternativo", "Latino");
		return generos;
	}
	
	
	@ModelAttribute("entradas")
	public List<Entrada> todasLasEntradas(){
		return entradaServicio.findAll();
	}
	
	@GetMapping("/eventos/entradas/comprar/{id}")
	public String comprarEntradas(@PathVariable("id") Long id, Model model,
			@RequestParam(name = "cantidad1", required = false, defaultValue = "0") Integer cantidad1,
			@RequestParam(name = "cantidad2", required = false, defaultValue = "0") Integer cantidad2,
			@RequestParam(name = "cantidad3", required = false, defaultValue = "0") Integer cantidad3) {
		Evento evento = eventoServicio.findById(id);
		
		if(evento != null) {
			model.addAttribute("evento", evento);
			model.addAttribute("general", entradaServicio.entradaTipoEventoId("Entrada General", id));
			model.addAttribute("vip", entradaServicio.entradaTipoEventoId("Entrada VIP", id));
			model.addAttribute("premium", entradaServicio.entradaTipoEventoId("Entrada Premium", id));
		}
		model.addAttribute("searchForm", new SearchBean());
		return "compra-entradas";
	}
	
	
	
	@PostMapping("/eventos/entradas/comprar/submit/{id}")
	public String submit(@PathVariable("id") Long id, Model model, @ModelAttribute("cantidad1") Integer cantidad1,
			 @ModelAttribute("cantidad2") Integer cantidad2,  @ModelAttribute("cantidad3") Integer cantidad3,
			 @ModelAttribute("entrada") Entrada entrada) {
		
		Evento evento = eventoServicio.findById(id);
		
		if(evento != null) {
			model.addAttribute("evento", evento);
			List<Entrada> entradas = 
					entradaServicio.comprarEntradas(entradaServicio.cantidades(cantidad1, cantidad2, cantidad3), evento);
			for(Entrada e : entradas) {
				cesta.addToCesta(e);
			}
			
		}

		return "redirect:/eventos/entradas/comprar/detalles/{id}";
	}
	
	
	@GetMapping("/eventos/entradas/comprar/detalles/{id}")
	public String detallesEntradas(@PathVariable("id") Long id, Model model) {
		Evento evento = eventoServicio.findById(id);
		
		if(evento != null) {
			model.addAttribute("evento", evento);
			model.addAttribute("cesta", cesta.getCesta());
			model.addAttribute("numProductosDiferentes", cesta.numeroTotalProductosDiferentes());
			model.addAttribute("subTotal", cesta.subTotal());
			model.addAttribute("numUnidades", cesta.numeroTotalDeUnidades());
			
		}
		model.addAttribute("searchForm", new SearchBean());
		return "compra-entradas-detalles";
	}
	
	@GetMapping("/finalizar")
	public String terminarCompra() {

		cesta.clear();
		return "redirect:/";
	}
	

	
	@GetMapping("/cesta/remove/{id}")
	public String removeFromCart(@PathVariable("id") Long id) {
		cesta.removeFromCesta(id);
		return "redirect: /cesta/mostrar";
	}
	
	@GetMapping("/cesta/clear/{id}")
	public String clearCart(@PathVariable("id") Long id) {
		
		cesta.clear();
		return "redirect:/eventos/entradas/comprar/detalles/{id}";
	}
	
	


}
