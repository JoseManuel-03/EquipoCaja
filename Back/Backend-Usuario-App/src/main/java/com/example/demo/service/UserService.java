package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.RegistroPractica;
import com.example.demo.model.Usuario;

import jakarta.validation.constraints.Size;

@Service
public interface UserService {

	public Usuario login(String user, String password) throws UserNotFoundException, UserUnauthorizedException;

	public void cambiarContraseña(Long getID, String getOldPassword, @Size(min = 8) String newPassword)
			throws UserUnauthorizedException, UserNotFoundException;

	public Integer horasTotales(Long id);

	public List<RegistroPractica> consultarDetalles(Long idUser) throws UserUnauthorizedException, UserNotFoundException;

	
}
