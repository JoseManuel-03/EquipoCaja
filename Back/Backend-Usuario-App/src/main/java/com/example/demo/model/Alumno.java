package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "alumnos")
@Data
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre_completo", nullable = false)
	private String nombreCompleto;

	@Column(nullable = false)
	private String ciclo;
	
	@Column(nullable = false)
	private String evaluacion;

	@Column(name = "anio_curso", nullable = false)
	private Integer anioCurso;

	@ManyToOne
	@JoinColumn(name = "tutor_docente_id")
	private TutoresDocente tutorDocente;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

}
