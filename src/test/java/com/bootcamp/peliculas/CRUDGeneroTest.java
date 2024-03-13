package com.bootcamp.peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bootcamp.dto.GeneroDTO;
import com.bootcamp.repository.GeneroRepository;
import com.bootcamp.service.GeneroService;
import com.bootcamp.service.imp.GeneroServiceImp;
import com.bootcamp.dto.mapper.GeneroMapper;

@SpringBootTest
public class CRUDGeneroTest {
    @Autowired
    private GeneroService generoService;
    @Autowired
    private GeneroRepository generoRepository;
    @Test
    public void validarCRUD () {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setNombreGenero("Fantasia");
        GeneroDTO generoDTO1 = generoService.guardarGenero(generoDTO);

        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setNombreGenero("Animacion");
        GeneroDTO generoDTO2 = generoService.guardarGenero(generoDTO);

        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setNombreGenero("Super Heroes");
        GeneroDTO generoDTO3 = generoService.guardarGenero(generoDTO);

        assertTrue(generoService.obtenerGeneros().size()>=3);

        GeneroDTO generoBuscado = generoService.obtenerPorId(generoDTO1.getId());

        assertTrue(generoBuscado.getNombreGenero().equals(generoDTO1.getNombreGenero()));

        GeneroDTO otroGenero = generoService.obtenerPorId(generoDTO2.getId());
        otroGenero.setNombreGenero("Terror");

        GeneroDTO generoModificado = generoService.guardarGenero(otroGenero);
        assertTrue(generoDTO2.getId().equals(generoModificado.getId()) && 
                    !generoDTO2.getNombreGenero().endsWith(generoModificado.getNombreGenero()));

        Boolean isDelete1 = generoService.eliminarGenero(generoDTO1.getId());
        Boolean isDelete2 = generoService.eliminarGenero(generoModificado.getId());
        Boolean isDelete3 = generoService.eliminarGenero(generoDTO3.getId());

        assertTrue(isDelete1 && isDelete2 && isDelete3);

        assertTrue(generoService.obtenerGeneros().isEmpty());
    }
}
