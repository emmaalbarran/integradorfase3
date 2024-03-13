package com.bootcamp.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.dto.GeneroDTO;
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

	@Override
	public List<GeneroDTO> obtenerGeneros() {
		List<Genero> generos = generoRepository.findAll();
		List<GeneroDTO> generoDTO = generos.stream().map(
			g-> {
				GeneroDTO generoDTO = generoMapper.generoToGeneroDTO(g);
				return generoDTO;
			}).collect(Collectors.toList());
			return generoDTO;
	}

	@Override
	public GeneroDTO guardarGenero(GeneroDTO generoDTO) {
		Genero genero = generoRepository.save(
			generoMapper.generoDTOtoGenero(generoDTO));
		GeneroDTO GeneroDTOGuardado = generoMapper.generoToGeneroDTO(genero);
		return generoDTOGuardado;
	}

	@Override
	public Boolean eliminarGenero(Integer id) {
		Boolean isDelete=false;
		Optional<Genero> genero = generoRepository.findById(id);
		if(genero.isPresent()) {
			generoRepository.deleteById(id);
			isDelete=true;
		}
		return isDelete;
	}

	@Override
	public GeneroDTO obtenerPorId(Integer id) {
		Optional<Genero> genero = generoRepository.findById(id);
		GeneroDTO generoPorDevolver = null;
		if (genero.isPresent()) {
			generoPorDevolver = generoMapper.generoToGeneroDTO(genero.get());
		}
	return generoPorDevolver;
	}
	
}