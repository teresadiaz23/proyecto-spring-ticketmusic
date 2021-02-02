package com.salesianostriana.dam.ticketmusic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.ticketmusic.model.Artista;
import com.salesianostriana.dam.ticketmusic.model.Ciudad;
import com.salesianostriana.dam.ticketmusic.model.Concierto;
import com.salesianostriana.dam.ticketmusic.model.Entrada;
import com.salesianostriana.dam.ticketmusic.model.EntradaGeneral;
import com.salesianostriana.dam.ticketmusic.model.EntradaPremium;
import com.salesianostriana.dam.ticketmusic.model.EntradaVIP;
import com.salesianostriana.dam.ticketmusic.model.Evento;
import com.salesianostriana.dam.ticketmusic.model.Festival;
import com.salesianostriana.dam.ticketmusic.model.Recinto;
import com.salesianostriana.dam.ticketmusic.model.Usuario;
import com.salesianostriana.dam.ticketmusic.service.ArtistaServicio;
import com.salesianostriana.dam.ticketmusic.service.CiudadServicio;
import com.salesianostriana.dam.ticketmusic.service.EntradaServicio;
import com.salesianostriana.dam.ticketmusic.service.EventoServicio;
import com.salesianostriana.dam.ticketmusic.service.RecintoServicio;
import com.salesianostriana.dam.ticketmusic.service.UsuarioServicio;

@SpringBootApplication
public class TicketMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketMusicApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(UsuarioServicio usuarioServicio, BCryptPasswordEncoder passwordEncoder,
			CiudadServicio ciudadServicio, RecintoServicio recintoServicio, ArtistaServicio artistaServicio,
			EventoServicio eventoServicio, EntradaServicio entradaServicio) {
		return args -> {
			
			
			for(Usuario usuario: usuarioServicio.findAll()) {
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
				usuarioServicio.edit(usuario);
			}
			
			Usuario u = new Usuario();
			u.setAdmin(false);
			u.setNombre("Usuario");
			u.setApellidos("Usuario");
			u.setEmail("usuario");
			u.setPassword(passwordEncoder.encode("1234"));
			
			
			usuarioServicio.save(u);
			
			
			Usuario a = new Usuario();
			a.setAdmin(true);
			a.setNombre("Admin");
			a.setApellidos("Admin");
			a.setEmail("admin");
			a.setPassword(passwordEncoder.encode("admin"));
			
			usuarioServicio.save(a);
			
			
			
			
			
			//Añadiendo ciudades
			Ciudad sevilla = new Ciudad("Sevilla");
			ciudadServicio.save(sevilla);
			Ciudad madrid = new Ciudad("Madrid");
			ciudadServicio.save(madrid);
			Ciudad barcelona = new Ciudad("Barcelona");
			ciudadServicio.save(barcelona);
			

			// Creamos un nuevo conjunto de recintos
			
			List<Recinto> recintos = Arrays.asList(
					new Recinto("Estadio de la Cartuja", 1000),
					new Recinto("Auditorio Rocio Jurado", 500),
					new Recinto("Estadio Benito Villamarín", 1000),
					new Recinto("Wizink Center", 500),
					new Recinto("Palacio Vitalegre Arena", 500),
					new Recinto("IFEMA", 1500),
					new Recinto("Palau Sant Jordi", 500)
					);
			
			
			for(Recinto r : recintos) {
				recintoServicio.save(r);
			}
			
			//Asignando recintos a las ciudades
			for (int i = 0; i < recintos.size(); i++) {
				if(i <= 2) {
					sevilla.addRecinto(recintos.get(i));
					
				}
				else if(i >= 3 && i <= 5) {
					madrid.addRecinto(recintos.get(i));
				}
				else if(i >= 6){
					barcelona.addRecinto(recintos.get(i));
				}
				recintoServicio.edit(recintos.get(i));
			}
			
			
			//Añadiendo Artistas
			Artista dLipa = new Artista("Dua Lipa");
			Artista lGaga = new Artista("Lady Gaga");
			Artista hStyles = new Artista("Harry Styles");
			Artista maluma = new Artista("Maluma");
			Artista jBalvin = new Artista("J Balvin");
			Artista dYankee = new Artista("Daddy Yankee");
			Artista extremoduro = new Artista("Extremoduro");
			Artista gRoses = new Artista("Guns N' Roses");
			Artista cCabello = new Artista("Camila Cabello");
			Artista aMori = new Artista("Alanis Morissette");
			Artista jpCooper = new Artista("JP Cooper");
			Artista mCaffeina = new Artista("Miss Caffeina");
			Artista dGuetta = new Artista("David Guetta");
			Artista kygo = new Artista("Kygo");
			Artista jBlue = new Artista("Jonas Blue");
			Artista mAnthony = new Artista("Marc Anthony");
			Artista shakira = new Artista("Shakira");
			Artista rMartin = new Artista("Ricky Martin");
			Artista bBunny = new Artista("Bad Bunny");
			Artista aFernandez = new Artista("Alejandro Fernández");
			Artista izal = new Artista("Izal");
			Artista nJam = new Artista("Nicky Jam");
			Artista hMendez = new Artista("Henry Méndez");
			Artista aitana = new Artista("Aitana");
			Artista sYatra = new Artista("Sebastian Yatra");
			Artista jMagan = new Artista("Juan Magan");
			Artista aWalker = new Artista("Alan Walker");
			Artista marshmello = new Artista("Marshmello");
			Artista bEilish = new Artista("Billie Eilish");
			Artista killers = new Artista("The Killers");
			Artista kLeon = new Artista("Kings of Leon");
			Artista ozuna = new Artista("Ozuna");
			Artista beckyG = new Artista("Becky G");
			Artista aMena = new Artista("Ana Mena");
			
			
			List<Artista> artistas = Arrays.asList(dLipa, lGaga, hStyles, maluma, jBalvin, dGuetta, dYankee,
					extremoduro, aMori, shakira, rMartin, mAnthony, mCaffeina, jpCooper, gRoses, cCabello, kygo, jBlue, bBunny,
					aFernandez, izal, nJam, hMendez, aitana, sYatra, jMagan, aWalker, marshmello, bEilish, killers, kLeon,
					ozuna, beckyG, aMena);
			
			
			
			for(Artista artista: artistas) {
				artistaServicio.save(artista);
			}
			
			//Añadiendo eventos
			List<Evento> conciertosPop = Arrays.asList(
					new Concierto("Future Nostalgia EU TOUR 2021", LocalDate.of(2021, 02, 14), LocalTime.of(21, 00), "Pop"),
					new Concierto("Future Nostalgia EU TOUR 2021", LocalDate.of(2021, 02, 16), LocalTime.of(21, 00), "Pop"),
					new Concierto("The Chromatica Ball", LocalDate.of(2020, 07, 24), LocalTime.of(21, 00), "Pop"),
					new Concierto("The Chromatica Ball", LocalDate.of(2020, 07, 26), LocalTime.of(21, 00), "Pop"),
					new Concierto("Love On Tour", LocalDate.of(2021, 02, 15), LocalTime.of(21, 00), "Pop"),
					new Concierto("Love On Tour", LocalDate.of(2021, 02, 17), LocalTime.of(21, 00), "Pop")
					
					);
			
			for(Evento evento: conciertosPop) {
				eventoServicio.save(evento);
			}
		
			
			List<Evento> conciertosRock = Arrays.asList(
					new Concierto("Extremoduro", LocalDate.of(2020, 05, 30), LocalTime.of(22, 00), "Rock"),
					new Concierto("Extremoduro", LocalDate.of(2020, 06, 15), LocalTime.of(22, 00), "Rock"),
					new Concierto("Guns N' Roses", LocalDate.of(2020, 06, 23), LocalTime.of(21, 30), "Rock"),
					new Concierto("Guns N' Roses", LocalDate.of(2020, 06, 25), LocalTime.of(21, 00), "Rock"),
					new Concierto("The Romance Tour", LocalDate.of(2020, 06, 30), LocalTime.of(21, 00), "Rock"),
					new Concierto("The Romance Tour", LocalDate.of(2020, 07, 01), LocalTime.of(21, 00), "Rock")
					);
			
			for(Evento evento: conciertosRock) {
				eventoServicio.save(evento);
			}
		
			
			List<Evento> conciertosReggaeton = Arrays.asList(
					new Concierto("11:11 World Tour", LocalDate.of(2020, 9, 20), LocalTime.of(21, 00), "Reggaeton"),
					new Concierto("11:11 World Tour", LocalDate.of(2020, 9, 21), LocalTime.of(21, 00), "Reggaeton"),
					new Concierto("Daddy Yankee World Tour", LocalDate.of(2020, 06, 20), LocalTime.of(22, 00), "Reggaeton"),
					new Concierto("Daddy Yankee World Tour", LocalDate.of(2020, 07, 04), LocalTime.of(22, 00), "Reggaeton"),
					new Concierto("YHLQMDLG Tour", LocalDate.of(2020, 8, 10), LocalTime.of(21, 00), "Reggaeton"),
					new Concierto("YHLQMDLG Tour", LocalDate.of(2020, 8, 14), LocalTime.of(21, 00), "Reggaeton")
					);
			
			for(Evento evento: conciertosReggaeton) {
				eventoServicio.save(evento);
			}
		
			
			List<Evento> conciertosLatino = Arrays.asList(
					new Concierto("Opus Tour", LocalDate.of(2021, 06, 9), LocalTime.of(22, 00), "Latino"),
					new Concierto("Opus Tour", LocalDate.of(2021, 06, 24), LocalTime.of(22, 00), "Latino"),
					new Concierto("El Dorado World Tour", LocalDate.of(2020, 07, 28), LocalTime.of(21, 00), "Latino"),
					new Concierto("El Dorado World Tour", LocalDate.of(2021, 07, 30), LocalTime.of(21, 00), "Latino"),
					new Concierto("Hecho en México", LocalDate.of(2020, 12, 8), LocalTime.of(21, 00), "Latino"),
					new Concierto("Hecho en México", LocalDate.of(2021, 12, 10), LocalTime.of(21, 00), "Latino")
					);
			
			for(Evento evento: conciertosLatino) {
				eventoServicio.save(evento);
			}
		
			
			List<Evento> conciertosIndie = Arrays.asList(
					new Concierto("Alanis Morissette", LocalDate.of(2020, 10, 17), LocalTime.of(21, 00), "Indie y Alternativo"),
					new Concierto("Alanis Morissette", LocalDate.of(2020, 10, 19), LocalTime.of(21, 00), "Indie y Alternativo"),
					new Concierto("JP Cooper", LocalDate.of(2020, 11, 15), LocalTime.of(21, 00), "Indie y Alternativo"),
					new Concierto("JP Cooper", LocalDate.of(2020, 11, 16), LocalTime.of(21, 00), "Indie y Alternativo"),
					new Concierto("IZAL", LocalDate.of(2021, 10, 06), LocalTime.of(21, 30), "Indie y Alternativo"),
					new Concierto("IZAL", LocalDate.of(2021, 10, 07), LocalTime.of(21, 30), "Indie y Alternativo")
					);
			
			for(Evento evento: conciertosIndie) {
				eventoServicio.save(evento);
			}
		
			
			
			//Festivales
			List<Evento> festivales = Arrays.asList(
					new Festival("Puro Reggaeton Festival", LocalDate.of(2020, 06, 26), LocalTime.of(14, 00)),
					new Festival("Puro Latino Fest", LocalDate.of(2020, 07, 03), LocalTime.of(18, 00)),
					new Festival("Starlite Catalana Occidente", LocalDate.of(2020, 07, 22), LocalTime.of(22, 00)),
					new Festival("Barcelona Beach Festival", LocalDate.of(2020, 07, 11), LocalTime.of(17, 00)),
					new Festival("Primavera Sevilla Festival", LocalDate.of(2021, 10, 17), LocalTime.of(19, 00)),
					new Festival("Mad Cool Festival", LocalDate.of(2020, 07, 10), LocalTime.of(18, 00))
					);
			
			for(Evento evento: festivales) {
				eventoServicio.save(evento);
			}
			
			
			
			//Asignar recintos con los eventos
			for (int i = 0; i < recintos.size(); i++) {
				if(i == 0) {
					recintos.get(i).addEvento(conciertosRock.get(0));
					recintos.get(i).addEvento(conciertosReggaeton.get(3));
					recintos.get(i).addEvento(conciertosLatino.get(1));
				}
				if(i == 1) {
					recintos.get(i).addEvento(festivales.get(4));
					
				}
				if(i == 2) {
					recintos.get(i).addEvento(conciertosRock.get(2));
					recintos.get(i).addEvento(festivales.get(1));
					
				}
				if(i == 3) {
					recintos.get(i).addEvento(conciertosPop.get(1));
					recintos.get(i).addEvento(conciertosPop.get(2));
					recintos.get(i).addEvento(conciertosPop.get(4));
					recintos.get(i).addEvento(conciertosRock.get(3));
					recintos.get(i).addEvento(conciertosRock.get(5));
					recintos.get(i).addEvento(conciertosReggaeton.get(1));
					recintos.get(i).addEvento(conciertosReggaeton.get(4));
					recintos.get(i).addEvento(conciertosLatino.get(2));
					recintos.get(i).addEvento(conciertosLatino.get(4));
					recintos.get(i).addEvento(conciertosIndie.get(1));
					recintos.get(i).addEvento(conciertosIndie.get(4));
					recintos.get(i).addEvento(conciertosIndie.get(5));
					recintos.get(i).addEvento(festivales.get(2));
					recintos.get(i).addEvento(festivales.get(5));
					
				}
				if(i == 4) {
					recintos.get(i).addEvento(conciertosRock.get(1));
					recintos.get(i).addEvento(conciertosLatino.get(0));
					recintos.get(i).addEvento(conciertosIndie.get(3));
					
				}
				if(i == 5) {
					recintos.get(i).addEvento(festivales.get(0));
					
				}
				else {
					recintos.get(i).addEvento(conciertosPop.get(0));
					recintos.get(i).addEvento(conciertosPop.get(3));
					recintos.get(i).addEvento(conciertosPop.get(5));
					recintos.get(i).addEvento(conciertosRock.get(4));
					recintos.get(i).addEvento(conciertosReggaeton.get(0));
					recintos.get(i).addEvento(conciertosReggaeton.get(2));
					recintos.get(i).addEvento(conciertosReggaeton.get(5));
					recintos.get(i).addEvento(conciertosLatino.get(3));
					recintos.get(i).addEvento(conciertosLatino.get(5));
					recintos.get(i).addEvento(conciertosIndie.get(0));
					recintos.get(i).addEvento(conciertosIndie.get(2));
					recintos.get(i).addEvento(festivales.get(3));
					
				}
				recintoServicio.edit(recintos.get(i));
			}
			
			List<Evento> listaEventos = eventoServicio.findAll();
			
			List<Entrada> entradas = new ArrayList<>();
			
			
				
				
				
				for (int i = 0; i < listaEventos.size(); i++) {
					for (int j = 0; j < 30; j++) {
						
						if(j <= 9) {
							entradas.add(new EntradaGeneral("Entrada General", 60.0, false));
						}
						else if(j > 9 && j <= 19 ) {
							entradas.add(new EntradaVIP("Entrada VIP", 100.0, false));
						}
						else {
							entradas.add(new EntradaPremium("Entrada Premium", 120.0, false));
							
						}

					}
					
					
				}
				
				for(Entrada ent: entradas) {
					entradaServicio.save(ent);
				}
				
				entradas = entradaServicio.findAll();
				
				
				
				int x = 0;
				
				for (int j = 0; j < listaEventos.size(); j++) {
					do {
					
						listaEventos.get(j).addEntrada(entradas.get(x));
						x++;
						

					}while(x%30 != 0);
					
				}
				
				
				
				//Asignar conciertos pop con artistas
				for (int i = 0; i < conciertosPop.size(); i++) {
					if(i == 0 || i == 1) {
						
						conciertosPop.get(i).addArtista(dLipa);
					}
					if(i == 2 || i == 3) {
						conciertosPop.get(i).addArtista(lGaga);
					}
					if(i == 4 || i == 5) {
						conciertosPop.get(i).addArtista(hStyles);
					}
					
					
					
				}
				

				
				//Asignar conciertos rock con artistas
				for (int i = 0; i < conciertosRock.size(); i++) {
					if(i == 0 || i == 1) {
						conciertosRock.get(i).addArtista(extremoduro);
					}
					if(i == 2 || i == 3) {
						conciertosRock.get(i).addArtista(gRoses);
					}
					if(i == 4 || i == 5) {
						conciertosRock.get(i).addArtista(cCabello);
					}
					
					
				}
			
				//Asignar conciertos reggaeton con artistas
				for (int i = 0; i < conciertosReggaeton.size(); i++) {
					if(i == 0 || i == 1) {
						conciertosReggaeton.get(i).addArtista(maluma);
					}
					if(i == 2 || i == 3) {
						conciertosReggaeton.get(i).addArtista(dYankee);
					}
					if(i == 4 || i == 5) {
						conciertosReggaeton.get(i).addArtista(bBunny);
					}
					
				}
				
				//Asignar conciertos latino con artistas
				for (int i = 0; i < conciertosLatino.size(); i++) {
					if(i == 0 || i == 1) {
						conciertosLatino.get(i).addArtista(mAnthony);
					}
					if(i == 2 || i == 3) {
						conciertosLatino.get(i).addArtista(shakira);
					}
					if(i == 4 || i == 5) {
						conciertosLatino.get(i).addArtista(aFernandez);
					}

				}
				
				//Asignar conciertos indie con artistas
				for (int i = 0; i < conciertosIndie.size(); i++) {
					if(i == 0 || i == 1) {
						conciertosIndie.get(i).addArtista(aMori);
					}
					if(i == 2 || i == 3) {
						conciertosIndie.get(i).addArtista(jpCooper);
					}
					if(i == 4 || i == 5) {
						conciertosIndie.get(i).addArtista(izal);
					}

				}
				
				for (int i = 0; i < festivales.size(); i++) {
					if(i == 0) {
						festivales.get(i).addArtista(jMagan);
						//festivales.get(i).addArtista(dYankee);
						//festivales.get(i).addArtista(bBunny);
					}
					if(i == 1) {
						festivales.get(i).addArtista(ozuna);
						//festivales.get(i).addArtista(beckyG);
						//festivales.get(i).addArtista(bBunny);
						
						
					}
					if(i == 2) {
						//festivales.get(i).addArtista(jMagan);
						//festivales.get(i).addArtista(sYatra);
						festivales.get(i).addArtista(aitana);

						
					}
					if(i == 3) {
						//festivales.get(i).addArtista(marshmello);
						festivales.get(i).addArtista(aWalker);
						
					}
					if(i == 4) {
						//festivales.get(i).addArtista(aitana);
						//festivales.get(i).addArtista(aMena);
						festivales.get(i).addArtista(sYatra);
					
					}
					if(i == 5) {
						festivales.get(i).addArtista(bEilish);
						//festivales.get(i).addArtista(killers);
						//festivales.get(i).addArtista(kLeon);

					}
					
					
				}
				
				for(Evento evento: listaEventos) {
					eventoServicio.edit(evento);
				}
				for(Artista artista: artistas) {
					artistaServicio.edit(artista);
				}
				

			
		};
	}

}
