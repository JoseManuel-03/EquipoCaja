package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.model.FechaPractica;
import com.example.demo.model.RegistroPractica;
import com.example.demo.model.Usuario;

import jakarta.validation.constraints.Size;

@Service
public interface UserService {

	public Usuario login(String user, String password) throws UserNotFoundException, UserUnauthorizedException;

	public void cambiarContraseña(Long getID, String getOldPassword, @Size(min = 8) String newPassword)
			throws UserUnauthorizedException, UserNotFoundException;

	public List<RegistroPractica> consultarDetalles(Long idUser, LocalDate fechaInicio, LocalDate fechaFin)
			throws UserUnauthorizedException, UserNotFoundException;

	public void crearRegistro(RegistroPractica registroPractica) throws UserUnauthorizedException;

	public void borrarRegistro(Long id) throws UserUnauthorizedException;

	public List<FechaPractica> obtenerFechas(Long id, Integer anioCurso, String eva, LocalDate fechaDesde,
			LocalDate fechaHasta) throws UserUnauthorizedException;
}
