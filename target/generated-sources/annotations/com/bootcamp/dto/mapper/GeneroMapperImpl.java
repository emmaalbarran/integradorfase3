package com.bootcamp.dto.mapper;

import com.bootcamp.dto.GeneroDTO;
import com.bootcamp.entity.Genero;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-13T10:53:07-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class GeneroMapperImpl implements GeneroMapper {

    @Override
    public Genero generoDTOtoGenero(GeneroDTO generoDTO) {
        if ( generoDTO == null ) {
            return null;
        }

        Genero genero = new Genero();

        genero.setId( generoDTO.getId() );
        genero.setNombreGenero( generoDTO.getNombreGenero() );

        return genero;
    }

    @Override
    public GeneroDTO generoToGeneroDTO(Genero genero) {
        if ( genero == null ) {
            return null;
        }

        GeneroDTO generoDTO = new GeneroDTO();

        generoDTO.setId( genero.getId() );
        generoDTO.setNombreGenero( genero.getNombreGenero() );

        return generoDTO;
    }
}
