package com.salesianostriana.dam.ticketmusic.controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lowagie.text.DocumentException;
import com.salesianostriana.dam.ticketmusic.cesta.Cesta;
import com.salesianostriana.dam.ticketmusic.formbeans.SearchBean;
import com.salesianostriana.dam.ticketmusic.model.Entrada;
import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.model.LineaVenta;
import com.salesianostriana.dam.ticketmusic.model.Usuario;
import com.salesianostriana.dam.ticketmusic.model.Venta;
import com.salesianostriana.dam.ticketmusic.service.EntradaServicio;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;
import com.salesianostriana.dam.ticketmusic.service.PdfGenarator;
import com.salesianostriana.dam.ticketmusic.service.UsuarioServicio;
import com.salesianostriana.dam.ticketmusic.service.VentaServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController {
	
	private final EventoServicio eventoServicio;
	private final UsuarioServicio usuarioServicio;
	private final Cesta cesta;
	private final VentaServicio ventaServicio;
	private final EntradaServicio entradaServicio;
	
	private final PdfGenarator pdfGenarator;

	private final String templateName = "templatePDF.html";
	private final String templateName2 = "templatePDF2.html";

	private final String fileName = "TicketMusicEntrada.pdf";

	
	
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
		
		model.addAttribute("searchForm", new SearchBean());
		return "usuario/index";
	}
	
	
	@GetMapping("/entradas")
	public String verEntradas(@AuthenticationPrincipal Usuario usuario, Model model) {
		List<Venta> ventas = usuario.getVentas();
		List<LineaVenta> lineasVenta = new ArrayList<>();
			for(Venta v: ventas) {
				for(LineaVenta l : v.getLineasVenta()) {
					lineasVenta.add(l);
				}
			}
		model.addAttribute("searchForm", new SearchBean());
		model.addAttribute("usuario", usuario);
		
		
		model.addAttribute("entradas", lineasVenta.stream()
				.map(l->l.getEntrada()).collect(Collectors.toList()));
				
	
		return "usuario/entradas";
	}
	
	
	
	@RequestMapping(value="/submit", method={RequestMethod.POST, RequestMethod.GET})
	public String submitUsuario(@ModelAttribute("usuario") Usuario usuario,
			@AuthenticationPrincipal Usuario u, Model model) {
		u.setNombre(usuario.getNombre());
		u.setApellidos(usuario.getApellidos());
		u.setFechaNacimiento(usuario.getFechaNacimiento());
		u.setLocalidad(usuario.getLocalidad());
		usuarioServicio.save(u);
		
		return "redirect:/user/";
	}
	
	@RequestMapping(value="/email/submit", method={RequestMethod.POST, RequestMethod.GET})
	public String submitUsuarioEmail(@ModelAttribute("usuario") Usuario usuario,
			@AuthenticationPrincipal Usuario u, Model model) {
		u.setEmail(usuario.getEmail());
		usuarioServicio.save(u);
		return "redirect:/user/";
	}
	
	@RequestMapping(value="/password/submit", method={RequestMethod.POST, RequestMethod.GET})
	public String submitUsuarioPassword(@ModelAttribute("usuario") Usuario usuario,
			@AuthenticationPrincipal Usuario u, Model model,
			BCryptPasswordEncoder passwordEncoder) {
		u.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioServicio.save(u);
		
		return "redirect:/user/";
	}
	
	@GetMapping("/perfil/{id}")
	public String verPerfil(@PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioServicio.findById(id);
		
		if(usuario != null) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("searchForm", new SearchBean());
			return "usuario/perfil";
		}
		else {
			return "redirect:/user/perfil";
		}
		
		
	}
	
	@GetMapping("/comprar/pago/{id}")
	public String pagar(@PathVariable("id") Long id, Model model) {
		double gastosGestion = 3.0;
		Evento evento = eventoServicio.findById(id);
		
		if(evento != null) {
			model.addAttribute("evento", evento);
			model.addAttribute("cesta", cesta.getCesta());
			model.addAttribute("numProductosDiferentes", cesta.numeroTotalProductosDiferentes());
			model.addAttribute("importeTotal", cesta.importeTotal(gastosGestion));
			model.addAttribute("subTotal", cesta.subTotal());
			model.addAttribute("gastosGestion", gastosGestion);
			model.addAttribute("numUnidades", cesta.numeroTotalDeUnidades());
		}
		model.addAttribute("searchForm", new SearchBean());
		
		return "usuario/compra-entradas-pago";
	}
	
	@GetMapping("/comprar/confirmacion/{id}")
	public String comfirmarCompra(@PathVariable("id") Long id, Model model,
			@AuthenticationPrincipal Usuario usuario) {
		Evento evento = eventoServicio.findById(id);
		double gastosGestion = 3.0;
		Venta venta = new Venta(cesta.importeTotal(gastosGestion));
		if(evento != null) {
			model.addAttribute("evento", evento);
			model.addAttribute("cesta", cesta.getCesta());
			model.addAttribute("numProductosDiferentes", cesta.numeroTotalProductosDiferentes());
			model.addAttribute("importeTotal", cesta.importeTotal(gastosGestion));
			model.addAttribute("subTotal", cesta.subTotal());
			model.addAttribute("gastosGestion", gastosGestion);
			model.addAttribute("numUnidades", cesta.numeroTotalDeUnidades());
			
			ventaServicio.realizarCompra(cesta, usuario, gastosGestion, venta);
			ventaServicio.edit(venta);

	
		}
		model.addAttribute("searchForm", new SearchBean());
		
		return "usuario/compra-entradas-confirmada";
	}
	
	@GetMapping("/entrada/pdf/{id}/{idE}")
	public ResponseEntity<ByteArrayResource> entradaPDF(@PathVariable("id") Long id,
			@PathVariable("idE") Long idE, final HttpServletRequest request,
			final HttpServletResponse response) throws DocumentException {

		Evento evento = eventoServicio.findById(id);
		Entrada entrada = entradaServicio.findById(idE);

		Map<String, Object> mapParameter = new HashMap<String, Object>();
		mapParameter.put("cesta", cesta.getCesta());
		mapParameter.put("evento", evento);
		mapParameter.put("entrada", entrada);

		ByteArrayOutputStream byteArrayOutputStreamPDF = pdfGenarator.createPdf(templateName, mapParameter, request, response);
		ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName).contentType(MediaType.APPLICATION_PDF)
				.contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);

	}
	
	@GetMapping("/entradas/pdf/{id}")
	public ResponseEntity<ByteArrayResource> entradaPDF2(@PathVariable("id") Long id,
			final HttpServletRequest request,
			final HttpServletResponse response) throws DocumentException {

		
		Entrada entrada = entradaServicio.findById(id);
		

		Map<String, Object> mapParameter = new HashMap<String, Object>();
		
		mapParameter.put("entrada", entrada);

		ByteArrayOutputStream byteArrayOutputStreamPDF = pdfGenarator.createPdf(templateName2, mapParameter, request, response);
		ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName).contentType(MediaType.APPLICATION_PDF)
				.contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);

	}
	
	

}
