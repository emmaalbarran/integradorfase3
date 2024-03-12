package com.bootcamp.dto;

import java.util.List;

import lombok.Data;

@Data
public class GeneroDTO {
    private String nombreGenero;
	private Integer id;
    private List<String> generos;
}