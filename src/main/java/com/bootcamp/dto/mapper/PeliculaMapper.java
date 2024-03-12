package com.bootcamp.dto.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bootcamp.dto.PeliculaDTO;
import com.bootcamp.dto.ResumenPeliculaDTO;
import com.bootcamp.entity.Genero;
import com.bootcamp.entity.ImagenPelicula;
import com.bootcamp.entity.Pelicula;
import com.bootcamp.repository.GeneroRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PeliculaMapper {
	private GeneroRepository generoRepository;
	
	public Pelicula pelicutaDtotoPelicula(PeliculaDTO peliculaDTO) {
		Pelicula pelicula = new Pelicula();
		pelicula.setNombre(peliculaDTO.getNombre());
		pelicula.setUrlWeb(peliculaDTO.getUrlWeb());
		ImagenPelicula imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo(peliculaDTO.getNombreImagen());
		imagenPelicula.setImagen(peliculaDTO.getImagenPelicula());
		pelicula.setImagenPelicula(imagenPelicula);
		List<Genero> generos = new ArrayList();
		for(String g: peliculaDTO.getGeneros()) {
			generos.add(generoRepository.findByNombreGenero(g));
		}
		pelicula.setGeneros(generos);
		return pelicula;
	}
	
	public ResumenPeliculaDTO peliculaToResumenPeliculaDTO(Pelicula pelicula) {
		ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();

		resumenPeliculaDTO.setNombre(pelicula.getNombre());
		resumenPeliculaDTO.setUrlWeb(pelicula.getUrlWeb());
		resumenPeliculaDTO.setNombreImagen(pelicula.getImagenPelicula().getNombreArchivo());
		String generosString = pelicula.getGeneros().stream().map(
						g->g.getNombreGenero()).collect(Collectors.joining(" - "));
		resumenPeliculaDTO.setStringGeneros(generosString);
		return resumenPeliculaDTO;

		// resumenPeliculaDTO.setNombreImagen(pelicula.getImagenPelicula().getNombreArchivo());
		// String generosString = pelicula.getGeneros().stream().map(
		// 					g->g.getNombreGenero()).collect(Collectors.joining(" - "));
		// resumenPeliculaDTO.setStringGeneros(generosString);
		// resumenPeliculaDTO.setTitulo(pelicula.getTitulo());
		// resumenPeliculaDTO.setUrlWeb(pelicula.getUrlWeb());
		// return resumenPeliculaDTO;
	}
	
	// public PeliculaDTO peliculaToPeliculaDTO(Pelicula pelicula) {
	// 	PeliculaDTO peliculaDTO = new PeliculaDTO();
	// 	List<String> generosString = pelicula.getGeneros().stream()
	// 					.map(g->g.getNombreGenero()).collect(Collectors.toList());
	// 	peliculaDTO.setGeneros(generosString);
	// 	peliculaDTO.setImagenPelicula(pelicula.getImagenPelicula().getImagen());
	// 	peliculaDTO.setTitulo(pelicula.getTitulo());
	// 	peliculaDTO.setUrlWeb(pelicula.getUrlWeb());
	// 	return peliculaDTO;
	// }
	
}