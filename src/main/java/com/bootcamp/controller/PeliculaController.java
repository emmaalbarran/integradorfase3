package com.bootcamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bootcamp.dto.PeliculaDTO;
import com.bootcamp.dto.ResumenPeliculaDTO;
import com.bootcamp.service.GeneroService;
import com.bootcamp.service.PeliculaService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/movies")
public class PeliculaController {
	@Autowired
	private PeliculaService peliculaService;
	@Autowired
	private GeneroService generoService;
	
	@PostMapping(value = "/save",consumes = {MediaType.APPLICATION_JSON_VALUE,
											 MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ResumenPeliculaDTO> registrarPelicula(@RequestPart("movie") String movie,
																@RequestPart("file") List<MultipartFile> files){
		ResumenPeliculaDTO resumenPeliculaDTO = peliculaService.registrarPelicula(movie, files.get(0));
		return new ResponseEntity<>(resumenPeliculaDTO,HttpStatus.CREATED);
	}
	
	@GetMapping("/findbytitle/{nombre}")
	public ResponseEntity<List<PeliculaDTO>> buscarPorTitulo(@PathVariable String nombre){
		List<PeliculaDTO> peliculas = peliculaService.buscarPorNombre(nombre);
		return ResponseEntity.ok(peliculas);
	}
	
	@GetMapping("/findbygenre/{genero}")
	public ResponseEntity<List<PeliculaDTO>> buscarPorGenero(@PathVariable String genero){
		List<PeliculaDTO> peliculas = generoService.obtenerPorGenero(genero);
		return ResponseEntity.ok(peliculas);
	}
}


