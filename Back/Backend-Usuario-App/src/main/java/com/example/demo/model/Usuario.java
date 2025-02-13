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
@Table(name = "usuarios")
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre_usuario", nullable = false)
	private String nombreUsuario;

	@Column(nullable = false)
	private String contraseña;

	@Column(nullable = false)
	private String perfil;

	@ManyToOne
	@JoinColumn(name = "usuario_asociado",nullable = false)

	private Alumno usuarioAsociado;

	@Column(nullable = false)
	private Boolean activo;
}
