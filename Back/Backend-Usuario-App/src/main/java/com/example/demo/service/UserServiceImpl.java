package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.example.demo.model.RegistroPractica;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.UserRepository;

import jakarta.validation.constraints.Size;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Usuario login(String user, String password) throws UserNotFoundException, UserUnauthorizedException {

		try {
			Optional<Usuario> usuarioOpt = userRepository.findOneByNombreUsuario(user);
			if (!usuarioOpt.isPresent()) {
				throw new UserNotFoundException("Usuario no encontrado");
			}

			Usuario usuario = usuarioOpt.get();
			String passwordCipher = DigestUtils.sha256Hex(password);
			if (!usuario.getContraseña().equals(passwordCipher)) {
				throw new UserUnauthorizedException("Password de usuario incorrecta");
			}

			return usuario;

		} catch (DataAccessException e) {
			throw new UserUnauthorizedException("No autorizado para entrar");
		}

	}

	@Override
	public void cambiarContraseña(Long getID, String getOldPassword, @Size(min = 8) String newPassword)
			throws UserUnauthorizedException, UserNotFoundException {
		try {

			if (newPassword.equals(getOldPassword)) {
				throw new UserUnauthorizedException("La password nueva no puede ser igual a la antigua");
			}
			Optional<Usuario> usuarioOpt = userRepository.findById(getID);
			if (!usuarioOpt.isPresent()) {
				throw new UserNotFoundException("Usuario no encontrado");
			}
			Usuario usuario = usuarioOpt.get();

			String passwordCipherOld = DigestUtils.sha256Hex(getOldPassword);
			if (!usuario.getContraseña().equals(passwordCipherOld)) {
				throw new UserUnauthorizedException("El password no es correcto");
			}

			String passwordCipherNew = DigestUtils.sha256Hex(newPassword);
			usuario.setContraseña(passwordCipherNew);
			userRepository.save(usuario);

		} catch (DataAccessException e) {
			throw new UserUnauthorizedException("No autorizado");
		}

	}

	@Override
	public List<RegistroPractica> consultarDetalles(Long idUser)
			throws UserUnauthorizedException, UserNotFoundException {
		try {

		} catch (DataAccessException e) {
			throw new UserUnauthorizedException("No autorizado");
		}
		return null;
	}

	@Override
	public Integer horasTotales(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
