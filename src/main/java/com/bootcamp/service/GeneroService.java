package com.bootcamp.service;

import java.util.List;

import com.bootcamp.dto.PeliculaDTO;

public interface GeneroService {
	List<PeliculaDTO> obtenerPorGenero(String genero);

}
