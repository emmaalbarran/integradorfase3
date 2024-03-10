package com.bootcamp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "peliculas")
public class Pelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "nombre",nullable = false,length = 150)
	private String titulo;
	@Column(name = "urlweb",nullable = false)
	private String urlWeb;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
	@JoinTable(name = "peliculas_generos",
				joinColumns = {@JoinColumn(name="pel_id")},
				inverseJoinColumns = {@JoinColumn(name="gen_id")})
	private List<Genero> generos;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imagen_id",referencedColumnName = "id",nullable = false)
	private ImagenPelicula imagenPelicula;
	@Column(name = "mayores_de_14", nullable = false)
    private boolean aptaMayoresDe14;
}
