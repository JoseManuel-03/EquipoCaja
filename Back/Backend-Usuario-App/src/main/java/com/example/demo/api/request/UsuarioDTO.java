package com.example.demo.api.request;

import java.util.List;

import com.example.model.RegistroPractica;
import com.example.model.Usuario;

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
        this.nombreCompleto = user.getUsuarioAsociado().getNombreCompleto();
        this.tutorDocente = user.getUsuarioAsociado().getTutorDocente().getNombreCompleto();
        this.ciclo = user.getUsuarioAsociado().getCiclo();
        this.año = user.getUsuarioAsociado().getAnioCurso();
        this.evaluacion = user.getUsuarioAsociado().getEvaluacion();
        this.empresaAsignada = user.getUsuarioAsociado().getEmpresa().getNombreEmpresa();
        this.cantidadHoras = (list != null) 
                             ? list.stream().mapToInt(RegistroPractica::getCantidadHoras).sum()
                             : 0;
    }


    

   
}
