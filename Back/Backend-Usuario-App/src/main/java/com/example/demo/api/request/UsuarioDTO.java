package com.example.demo.api.request;

import java.util.List;

import com.example.demo.model.Alumno;
import com.example.demo.model.RegistroPractica;
import com.example.demo.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
	private String nombreUsuario;
	private String contraseña;
	private String perfil;
	private Alumno usuarioAsociado;
	private Boolean activo;
	private Double cantidadHoras;

	public UsuarioDTO(Usuario user, List<RegistroPractica> list) {
		this.id = user.getId();
		this.nombreUsuario = user.getNombreUsuario();
		this.contraseña = user.getContraseña();
		this.perfil = user.getPerfil();
		this.usuarioAsociado = user.getUsuarioAsociado();
		this.activo = user.getActivo();

		this.cantidadHoras = (list != null)
				? list.stream().mapToDouble(r -> (r.getCantidadHoras() != null) ? r.getCantidadHoras() : 0).sum()
				: 0;

	}

}
