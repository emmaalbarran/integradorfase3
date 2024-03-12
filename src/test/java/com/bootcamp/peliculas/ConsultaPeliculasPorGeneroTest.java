package com.bootcamp.peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bootcamp.dto.PeliculaDTO;
import com.bootcamp.entity.Genero;
import com.bootcamp.entity.ImagenPelicula;
import com.bootcamp.entity.Pelicula;
import com.bootcamp.repository.GeneroRepository;
import com.bootcamp.repository.PeliculaRepository;
import com.bootcamp.service.GeneroService;

@SpringBootTest
public class ConsultaPeliculasPorGeneroTest {
	@Autowired
	private GeneroRepository generoRepository;
	@Autowired
	private PeliculaRepository peliculaRepository;
	@Autowired
	private GeneroService generoService;
	
	
	@Test
	public void validarConsulta() {
		try {
			// insnancias de generos
			//GENEROS
			Genero genero = new Genero();
			genero.setNombreGenero("Animacion");			
			Genero genero1 = generoRepository.save(genero);
			
			genero = new Genero();
			genero.setNombreGenero("Fantasia");			
			Genero genero2 = generoRepository.save(genero);
			
			genero = new Genero();
			genero.setNombreGenero("SuperHeroes");			
			Genero genero3 = generoRepository.save(genero);
			
			// instancia de pelicula 1
			Pelicula pelicula = new Pelicula();
			pelicula.setNombre("Super Mario: La película");
			pelicula.setUrlWeb("https://m.cinesargentinos.com.ar/pelicula/9043-super-mario-bros-la-pelicula/");
			List<Genero> generos = new ArrayList<>();
			generos.add(genero1);
			generos.add(genero2);
			pelicula.setGeneros(generos);
			InputStream inputStream = getClass().getResourceAsStream("/peliculas/mario.jpg");
			ImagenPelicula imagenPelicula = new ImagenPelicula();
			imagenPelicula.setNombreArchivo("mario.jpg");
			imagenPelicula.setImagen(inputStream.readAllBytes());
			pelicula.setImagenPelicula(imagenPelicula);
			
			Pelicula peliculaGuardada1 = peliculaRepository.save(pelicula);
			
			// instancia de pelicula 2
			pelicula = new Pelicula();
			pelicula.setNombre("Superman: Hombre del mañana");
			pelicula.setUrlWeb("https://www.filmaffinity.com/es/film362098.html");
			generos = new ArrayList<>();
			generos.add(genero1);
			generos.add(genero3);
			pelicula.setGeneros(generos);
			inputStream = getClass().getResourceAsStream("/peliculas/superman.jpg");
			imagenPelicula = new ImagenPelicula();
			imagenPelicula.setNombreArchivo("superman.jpg");
			imagenPelicula.setImagen(inputStream.readAllBytes());
			pelicula.setImagenPelicula(imagenPelicula);
			
			Pelicula peliculaGuardada2 = peliculaRepository.save(pelicula);
			
			// instancia de pelicula 3
			pelicula = new Pelicula();
			pelicula.setNombre("Aquaman y el Reino Perdido");
			pelicula.setUrlWeb("https://m.cinesargentinos.com.ar/pelicula/8260-aquaman-y-el-reino-perdido/");
			generos = new ArrayList<>();
			generos.add(genero2);
			generos.add(genero3);
			pelicula.setGeneros(generos);
			inputStream = getClass().getResourceAsStream("/peliculas/Aquaman2.jpg");
			imagenPelicula = new ImagenPelicula();
			imagenPelicula.setNombreArchivo("Aquaman2.jpg");
			imagenPelicula.setImagen(inputStream.readAllBytes());
			pelicula.setImagenPelicula(imagenPelicula);
			
			Pelicula peliculaGuardada3 = peliculaRepository.save(pelicula);
		
			// Consulta 
			
			List<PeliculaDTO> peliculas = generoService.obtenerPorGenero("Fantasia");
			
			for(PeliculaDTO p: peliculas) {
				System.out.println(p.getNombre());
			}
			
			assertTrue(peliculas.size()==2);
			
			peliculaRepository.deleteAll();
			generoRepository.deleteAll();
			
			assertTrue(peliculaRepository.findAll().isEmpty() && generoRepository.findAll().isEmpty());
			
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
