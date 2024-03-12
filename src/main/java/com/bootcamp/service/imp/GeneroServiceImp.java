package com.bootcamp.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.dto.PeliculaDTO;
import com.bootcamp.dto.mapper.PeliculaMapper;
import com.bootcamp.entity.Genero;
import com.bootcamp.repository.GeneroRepository;
import com.bootcamp.service.GeneroService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GeneroServiceImp implements GeneroService{
	private GeneroRepository generoRepository;
	private PeliculaMapper peliculaMapper;

	@Override
	public List<PeliculaDTO> obtenerPorGenero(String genero) {
		Genero generoBuscado = generoRepository.findByNombreGenero(genero);
		
		List<PeliculaDTO> peliculas = generoBuscado.getPeliculas().stream().map(
				p->{
					PeliculaDTO dto = peliculaMapper.peliculaToPeliculaDTO(p);
					return dto;
				}).collect(Collectors.toList());		
		return peliculas;
	}

}