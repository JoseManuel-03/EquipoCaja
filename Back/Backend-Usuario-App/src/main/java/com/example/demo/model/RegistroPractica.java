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
@Table(name = "registros_practicas")
@Data
public class RegistroPractica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "alumno_id", nullable = false)
	private Alumno alumno;

	@ManyToOne
	@JoinColumn(name = "fecha_id", nullable = false)
	private FechaPractica fecha;

	@Column(name = "cantidad_horas", nullable = false)
	private Integer cantidadHoras;

	@Column(nullable = false)
	private String descripcion;

}
