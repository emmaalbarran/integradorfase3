package com.bootcamp.service.imp;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bootcamp.dto.PeliculaDTO;
import com.bootcamp.dto.ResumenPeliculaDTO;
import com.bootcamp.dto.mapper.PeliculaMapper;
import com.bootcamp.entity.Pelicula;
import com.bootcamp.repository.PeliculaRepository;
import com.bootcamp.service.PeliculaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PeliculaServiceImp implements PeliculaService{
	private PeliculaMapper peliculaMapper;
	private PeliculaRepository peliculaRepository;
	
	@Override
	public ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen) {
		ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
		PeliculaDTO peliculaDTO = new PeliculaDTO();

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			peliculaDTO = objectMapper.readValue(movie, PeliculaDTO.class);
			peliculaDTO.setImagenPelicula(archivoImagen.getBytes());

			Pelicula pelicula = peliculaRepository.save(peliculaMapper.pelicutaDtotoPelicula(peliculaDTO));

			resumenPeliculaDTO = peliculaMapper.peliculaToResumenPeliculaDTO(pelicula);

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		 return resumenPeliculaDTO;
	}

	@Override
	public List<PeliculaDTO> buscarPorNombre(String nombre) {
		List<Pelicula> peliculas = peliculaRepository.findByNombreContainingIgnoreCase(nombre);
		System.out.println("el repository se lleva a cabo");
		List<PeliculaDTO> peliculasDTO = peliculas.stream()
				.map(p->{
					PeliculaDTO peliculaDTO = peliculaMapper.peliculaToPeliculaDTO(p);
					return peliculaDTO;
				}).collect(Collectors.toList());
				
		return peliculasDTO;
	}

}
