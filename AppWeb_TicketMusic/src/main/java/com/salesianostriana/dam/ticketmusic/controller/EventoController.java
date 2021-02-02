package com.salesianostriana.dam.ticketmusic.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.ticketmusic.model.Concierto;
import com.salesianostriana.dam.ticketmusic.model.Entrada;
import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.model.Festival;
import com.salesianostriana.dam.ticketmusic.service.ArtistaServicio;
import com.salesianostriana.dam.ticketmusic.service.EntradaServicio;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;
import com.salesianostriana.dam.ticketmusic.service.RecintoServicio;

@Controller
@RequestMapping("/admin/evento")
public class EventoController {
	
	private final EventoServicio eventoServicio;
	private final RecintoServicio recintoServicio;
	private final ArtistaServicio artistaServicio;
	private final EntradaServicio entradaServicio;
	private final String BASE_IMAGE_PATH;
	
	public EventoController(EventoServicio eventoServicio, RecintoServicio recintoServicio,
			ArtistaServicio artistaServicio, EntradaServicio entradaServicio, @Value("${image.base-path:/files}") String path) {
		this.eventoServicio = eventoServicio;
		this.recintoServicio = recintoServicio;
		this.artistaServicio = artistaServicio;
		this.entradaServicio = entradaServicio;
		this.BASE_IMAGE_PATH = path;
	}
	
	@ModelAttribute("base_image_path")
	public String baseImagePath() {
		return this.BASE_IMAGE_PATH;
	}
	
	
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
	public String administrarEvento(Model model, @RequestParam(name="q", required=false) String query,
			@RequestParam(name = "precio1", required = false, defaultValue = "1.0") Double precio1,
			@RequestParam(name = "precio2", required = false, defaultValue = "1.0") Double precio2,
			@RequestParam(name = "precio3", required = false, defaultValue = "1.0") Double precio3) {
		
		List<Evento> resultado = (query == null) ? eventoServicio.findAll() : eventoServicio.buscador(query);
		model.addAttribute("evento", new Concierto());
		model.addAttribute("festival", new Festival());
		model.addAttribute("eventos", resultado);
		model.addAttribute("recintos", recintoServicio.findAll());
		model.addAttribute("artistas", artistaServicio.findAll());
		model.addAttribute("precio1", precio1);
		model.addAttribute("precio2", precio2);
		model.addAttribute("precio3", precio3);
		
		
		return "administrador/evento";
	}
	
	@PostMapping("/concierto/submit")
	public String submitConcierto(@ModelAttribute("evento") Concierto concierto,  MultipartFile file,
			Model model, @ModelAttribute("precio1") Double precio1,  @ModelAttribute("precio2") Double precio2,
			 @ModelAttribute("precio3") Double precio3) {
		if (!file.isEmpty()) {
			eventoServicio.save(concierto, file);
			
		}
			
		else {
			eventoServicio.save(concierto);
			
		}
		for(Entrada e: entradaServicio.crearEntradas2(concierto, precio1, precio2, precio3)) {
			concierto.addEntrada(e);
			entradaServicio.save(e);
	
		}
		model.addAttribute("recintos", recintoServicio.findAll());
		model.addAttribute("artistas", artistaServicio.findAll());
		return "redirect:/admin/evento/";
	}
	
	
	@PostMapping("/festival/submit")
	public String submitFestival(@ModelAttribute("festival") Festival festival,  MultipartFile file, Model model) {
		if (!file.isEmpty()) {
			eventoServicio.save(festival, file);
		}
			
		else {
			eventoServicio.save(festival);	
		}
		model.addAttribute("recintos", recintoServicio.findAll());
		model.addAttribute("artistas", artistaServicio.findAll());

		return "redirect:/admin/evento/";
	}
	
	
	@GetMapping("/concierto/editar/{id}")
	public String editarConcierto(@PathVariable("id") Long id, Model model) {
		Evento evento = eventoServicio.findById(id);
		model.addAttribute("recintos", recintoServicio.findAll());
		model.addAttribute("artistas", artistaServicio.findAll());
		
		if(evento != null) {
			model.addAttribute("evento", evento);
			return "administrador/evento";
			
		}
		else {
			return "redirect:/admin/evento/";
		}
		
	}
	
	@PostMapping("/concierto/editar/submit")
	public String procesarFormularioEdicionConcierto(@ModelAttribute("evento") Concierto concierto, MultipartFile file) {
		
		if (!file.isEmpty()) {
			eventoServicio.edit(concierto, file);
		}
			
		else {
			eventoServicio.edit(concierto);	
		}
		
		return "redirect:/admin/evento/";
	}
	
	@GetMapping("/festival/editar/{id}")
	public String editarFestival(@PathVariable("id") Long id, Model model) {
		Evento evento = eventoServicio.findById(id);
		model.addAttribute("recintos", recintoServicio.findAll());
		model.addAttribute("artistas", artistaServicio.findAll());
		
		if(evento != null) {
			model.addAttribute("evento", evento);
			return "administrador/evento";
			
		}
		else {
			return "redirect:/admin/evento/";
		}
		
	}
	
	@PostMapping("/festival/editar/submit")
	public String procesarFormularioEdicionFestival(@ModelAttribute("festival") Festival festival, MultipartFile file) {

		if (!file.isEmpty()) {
			eventoServicio.edit(festival, file);
		}
			
		else {
			eventoServicio.edit(festival);	
		}
		
		return "redirect:/admin/evento/";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarConcierto(@PathVariable("id") Long id, Model model, MultipartFile file) {
		Evento evento = eventoServicio.findById(id);
		
		if(evento != null) {

			eventoServicio.delete(evento);
			
		}
		
		return "redirect:/admin/evento/";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
