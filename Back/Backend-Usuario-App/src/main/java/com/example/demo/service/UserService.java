package com.example.demo.service;


import java.util.List;

import com.example.demo.model.RegistroPractica;
import com.example.demo.model.Usuario;

import jakarta.validation.constraints.Size;

public interface UserService {

	public Usuario login(String user, String password);

	public Usuario cambiarContraseña(Object getID, Object getOldPassword, @Size(min = 8) Object newPassword);

	public Usuario verDatos(Long idUser); 

	public List<RegistroPractica> consultarDetalles(Long idUser);

	public Integer horasTotales(Long id);
	
}
