package com.bootcamp.dto.mapper;

import org.mapstruct.Mapper;
import com.bootcamp.dto.GeneroDTO;
import com.bootcamp.entity.Genero;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeneroMapper {
    @Mapping(target = "peliculas", ignore = true)
    Genero generoDTOtoGenero (GeneroDTO generoDTO);
    GeneroDTO generoToGeneroDTO (Genero genero);
}
