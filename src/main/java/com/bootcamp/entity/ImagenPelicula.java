package com.bootcamp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "imagenes")
public class ImagenPelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "nombre_archivo",nullable = false,length = 100)
	private String nombreArchivo;
	@Lob
	@Column(name = "imagen", nullable = false, columnDefinition = "LONGBLOB")
	private byte[] imagen;
	@OneToOne(mappedBy = "imagenPelicula")
	private Pelicula pelicula;

}
