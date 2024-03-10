package com.bootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.entity.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
	Genero findByNombreGenero(String nombreGenero);
}
