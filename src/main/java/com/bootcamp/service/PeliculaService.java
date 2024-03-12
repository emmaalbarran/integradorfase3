package com.bootcamp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bootcamp.dto.PeliculaDTO;
import com.bootcamp.dto.ResumenPeliculaDTO;

public interface PeliculaService {
	ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen);
	List<PeliculaDTO> buscarPorNombre(String nombre);
}
