package com.bootcamp.service;

import java.util.List;

import com.bootcamp.dto.GeneroDTO;
import com.bootcamp.dto.PeliculaDTO;

public interface GeneroService {
	List<PeliculaDTO> obtenerPorGenero(String genero);

	List<GeneroDTO> obtenerGeneros();
	GeneroDTO guardarGenero (GeneroDTO generoDTO);
	Boolean eliminarGenero(Integer id);
	GeneroDTO obtenerPorId(Integer id);
}
