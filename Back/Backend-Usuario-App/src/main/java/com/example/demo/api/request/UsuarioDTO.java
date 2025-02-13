package com.example.demo.api.request;

import java.util.List;

import com.example.demo.model.RegistroPractica;
import com.example.demo.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {
	private Long id;
	private String nombreCompleto;
	private String tutorDocente;
	private String ciclo;
	private Integer año;
	private String evaluacion;
	private String docente;
	private String empresaAsignada;
	private Integer cantidadHoras;

	public UsuarioDTO(Usuario user, List<RegistroPractica> list) {
		this.id = user.getId();
		this.nombreCompleto = (user.getUsuarioAsociado() != null) ? user.getUsuarioAsociado().getNombreCompleto()
				: "Sin asignar";
		this.tutorDocente = (user.getUsuarioAsociado() != null && user.getUsuarioAsociado().getTutorDocente() != null)
				? user.getUsuarioAsociado().getTutorDocente().getNombreCompleto()
				: "Sin asignar";
		this.ciclo = (user.getUsuarioAsociado() != null) ? user.getUsuarioAsociado().getCiclo() : "Sin asignar";

		this.año = user.getUsuarioAsociado().getAnioCurso();
		this.evaluacion = user.getUsuarioAsociado().getEvaluacion();
		this.empresaAsignada = (user.getUsuarioAsociado() != null && user.getUsuarioAsociado().getEmpresa() != null)
				? user.getUsuarioAsociado().getEmpresa().getNombreEmpresa()
				: "Sin asignar";
		this.cantidadHoras = (list != null)
				? list.stream().mapToInt(r -> (r.getCantidadHoras() != null) ? r.getCantidadHoras() : 0).sum()
				: 0;

	}

}
