package com.bootcamp.peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bootcamp.entity.Genero;
import com.bootcamp.entity.ImagenPelicula;
import com.bootcamp.entity.Pelicula;
import com.bootcamp.repository.GeneroRepository;
import com.bootcamp.repository.PeliculaRepository;

@SpringBootTest
public class AltaPeliculaTest {
	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private PeliculaRepository peliculaRepository;
	
	@Test
	public void validarNuevaPelicula() {

		try {
		Genero genero = new Genero();
		genero.setNombreGenero("Acción");

		Genero genero1 = generoRepository.save(genero);
		
		genero = new Genero();
		genero.setNombreGenero("Ciencia Ficción");

		Genero genero2 = generoRepository.save(genero);

		Pelicula pelicula = new Pelicula();
		pelicula.setNombre("Duna");
		pelicula.setUrlWeb("https://es.wikipedia.org/wiki/Dune_(pel%C3%ADcula_de_2021)");
		List<Genero> generos = new ArrayList<>();
		generos.add(genero1);
		generos.add(genero2);

		pelicula.setGeneros(generos);
		
		InputStream inputStream = getClass().getResourceAsStream("/peliculas/dune.jpg");
		ImagenPelicula imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo("mario.jpg");
		imagenPelicula.setImagen(inputStream.readAllBytes());

		pelicula.setImagenPelicula(imagenPelicula);
		
		Pelicula peliculaGuardada = peliculaRepository.save(pelicula);

		List<Pelicula> peliculas = peliculaRepository.findAll();

		assertTrue(peliculaRepository.findAll().isEmpty() && generoRepository.findAll().isEmpty());
		
	}catch(IOException e) {
		System.out.println(e.getMessage());
	}
	}
}