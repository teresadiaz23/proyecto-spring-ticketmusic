package com.salesianostriana.dam.ticketmusic.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.ticketmusic.formbeans.SearchBean;
import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.model.Usuario;
import com.salesianostriana.dam.ticketmusic.service.EntradaServicio;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;
import com.salesianostriana.dam.ticketmusic.service.UsuarioServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WelcomeController {
	
	private final UsuarioServicio usuarioServicio;
	private final EventoServicio eventoServicio;
	private final EntradaServicio entradaServicio;
	
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
	public String index(Model model) {
		model.addAttribute("eventos", eventoServicio.primeros6Eventos());
		model.addAttribute("searchForm", new SearchBean());
		
		return "index";
	}
	
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("emails", usuarioServicio.findAll().stream()
												.map(Usuario::getEmail)
												.collect(Collectors.toList()));
		return "login";
	}
	
	
	@PostMapping("/registro/submit")
	public String submitUsuario(@ModelAttribute("usuario") Usuario usuario, Model model,
			BCryptPasswordEncoder passwordEncoder) {

		usuarioServicio.save(usuario, passwordEncoder);
		return "redirect:/login";
	}
	
	
	@GetMapping("/eventos")
	public String eventos(Model model) {
		
		model.addAttribute("eventos", eventoServicio.eventosOrdenadosFecha());
		model.addAttribute("searchForm", new SearchBean());
		
		return "eventos";
	}

	
	/*Método para buscar eventos*/
	
	@PostMapping("/search")
	  public String searchEvento(@ModelAttribute("searchForm") SearchBean searchBean,
			 Model model){
	  	
		model.addAttribute("eventos", eventoServicio.buscador(searchBean.getSearch()));
	  return "eventos-buscados";
	  }
	
	

	
	@GetMapping("/conciertos")
	public String conciertos(@RequestParam(name="genero", required=false) String genero, Model model) {
		List<Evento> eventos;
		model.addAttribute("searchForm", new SearchBean());
		
		if(genero == null) {
			eventos = eventoServicio.listarTodosConciertos();
		}
		else {
			eventos = eventoServicio.encuentraPorGenero(genero);
		}
		
		model.addAttribute("conciertosGeneros", eventos);
		
		return "conciertos";
	}
	
	
	@GetMapping("/festivales")
	public String festivales(Model model) {
		model.addAttribute("festivales", eventoServicio.listarTodosFestivales());
		model.addAttribute("searchForm", new SearchBean());
		
		return "festivales";
	}
	@GetMapping("/eventos/entradas/{id}")
	public String entradas(@PathVariable("id") Long id, Model model) {
		Evento evento = eventoServicio.findById(id);
		
		if(evento != null) {
			model.addAttribute("evento", evento);
			model.addAttribute("general", entradaServicio.entradaTipoEventoId("Entrada General", id));
			model.addAttribute("vip", entradaServicio.entradaTipoEventoId("Entrada VIP", id));
			model.addAttribute("premium", entradaServicio.entradaTipoEventoId("Entrada Premium", id));
		}
		model.addAttribute("searchForm", new SearchBean());
		
		return "entradas";
	}


	
	@GetMapping("/contacto")
	public String contactar(Model model) {
		model.addAttribute("searchForm", new SearchBean());
		return "contacto";
	}
	
	

}
