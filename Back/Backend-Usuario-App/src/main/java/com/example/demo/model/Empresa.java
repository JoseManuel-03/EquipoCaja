package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "empresas")
@Data
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre_empresa", nullable = false)
	private String nombreEmpresa;

	@Column(name = "tutor_laboral_nombre", nullable = false)
	private String tutorLaboralNombre;

	@Column(name = "tutor_laboral_email", nullable = false)
	private String tutorLaboralEmail;

	@Column(name = "tutor_laboral_telefono", nullable = false)
	private String tutorLaboralTelefono;

	@Column(nullable = false)
	private Boolean activo;

}
