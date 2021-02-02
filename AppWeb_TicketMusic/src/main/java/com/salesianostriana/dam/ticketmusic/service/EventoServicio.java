package com.salesianostriana.dam.ticketmusic.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.repository.EventoRepository;
import com.salesianostriana.dam.ticketmusic.service.base.BaseService;
import com.salesianostriana.dam.ticketmusic.upload.DBStorageService;


@Service
public class EventoServicio extends BaseService<Evento, Long, EventoRepository>{
	
	
	private final DBStorageService dbStorageService;
	

	public EventoServicio(EventoRepository repo, DBStorageService dbStoreService) {
		super(repo);
		this.dbStorageService = dbStoreService;
		
	}

	

	/**
	 * Método que permite guardar una entidad que tiene una imagen.
	 * 
	 * @param c
	 * @param imagen
	 * @return
	 */
	public Evento save(Evento e, MultipartFile imagen) {
		// Pasos a seguir
		
		// 1) Transformar la imagen en un String
		String pathImagen = dbStorageService.store(imagen);
		// 2) Asignar esta cadena de caracteres con nuestra entidad
		e.setCartel(pathImagen);
		
		// 3) Almacenarla
		return this.save(e);
	}
	
	

	
	public Evento edit(Evento e, MultipartFile imagen) {
		String pathImagen = dbStorageService.store(imagen);
		e.setCartel(pathImagen);
		return this.edit(e);
	}



	/**
	 * Antes de eliminar nuestra entidad, tenemos que eliminar la imagen asociada.
	 */
	@Override
	public void delete(Evento e) {
		String idCartel = e.getCartel();
		if(idCartel != null) {
			dbStorageService.delete(Long.valueOf(idCartel));
		}
		
		super.delete(e);
	}

	/**
	 * Antes de eliminar nuestra entidad, tenemos que eliminar la imagen asociada.
	 */
	@Override
	public void deleteById(Long id) {
		Evento evento = findById(id);
		if (evento != null)
			delete(evento);
	}
	
	public List<Evento> listarTodosConciertos(){
		List<Evento> conciertosOrdenados = repositorio.listaConciertos();
		return conciertosOrdenados.stream()
				.sorted(Comparator.comparing(Evento::getFecha))
				.collect(Collectors.toList());
		
	}
	
	public List<Evento> listarTodosFestivales(){
		List<Evento> festivalesOrdenados = repositorio.listaFertivales();
		return festivalesOrdenados.stream()
				.sorted(Comparator.comparing(Evento::getFecha))
				.collect(Collectors.toList());
	}
	
	public List<Evento> primeros6Eventos(){
		return repositorio.primeros6Eventos();
	}
	
	public List<Evento> eventosOrdenadosFecha(){
		return repositorio.ordenarPorFecha();
	}
	
	public List<Evento> encuentraPorGenero(String genero){
		List<Evento> conciertosGenero = repositorio.listaConciertos();
		
		return conciertosGenero.stream()
				.filter(e -> e.sacarGenero().equalsIgnoreCase(genero))
				.collect(Collectors.toList());
		
	}
	
	public List<Evento> findByNombre(String nombre){
		return repositorio.findByNombreContainingIgnoreCase(nombre);
	}
	
	
	
	public List<Evento> buscador(String cadena) {
		
		return repositorio.buscarEventos(cadena.toLowerCase());
	}
	
	
	

}
