package com.example.demo.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.FechaPractica;
import com.example.demo.model.RegistroPractica;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.FechaRepository;
import com.example.demo.repositories.RegistroRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RegistroRepository registroRepository;

	@Autowired
	private FechaRepository fechaRepository;

	@Override
	public Usuario login(String user, String password) throws UserNotFoundException, UserUnauthorizedException {
		log.debug("El usuario: " + user + " haciendo login");
		try {

			Optional<Usuario> usuarioOpt = userRepository.findOneByNombreUsuario(user);
			if (!usuarioOpt.isPresent()) {
				log.debug("El usuario: " + user + " no encontrado");
				throw new UserNotFoundException("Usuario no encontrado");
			}

			Usuario usuario = usuarioOpt.get();

			if (!usuario.getContraseña().equals(password)) {
				log.debug("El usuario: " + user + " sin autorización");
				throw new UserUnauthorizedException("Password de usuario incorrecta");
			}
			log.debug("El usuario: " + user + " ha hecho login con éxito");
			return usuario;

		} catch (DataAccessException e) {
			log.debug("Error al hacer login", e);
			throw new UserUnauthorizedException("No autorizado para entrar", e);
		}

	}

	@Override
	@Transactional
	public void cambiarContraseña(Long getID, String getOldPassword, @Size(min = 8) String newPassword)
			throws UserUnauthorizedException, UserNotFoundException {
		log.debug("Actualizacion password de usuario con id: " + getID);
		try {

			if (newPassword.equals(getOldPassword)) {
				log.debug("Pass antigua igual a la nueva, no se hará el cambio ");
				throw new UserUnauthorizedException("La password nueva no puede ser igual a la antigua");
			}
			Optional<Usuario> usuarioOpt = userRepository.findById(getID);
			if (!usuarioOpt.isPresent()) {
				log.warn("El usuario indicado no existe con id " + getID);
				throw new UserNotFoundException("Usuario no encontrado");
			}
			Usuario usuario = usuarioOpt.get();

			if (!usuario.getContraseña().equals(getOldPassword)) {
				log.debug("Pass indicada para cambio incorrecta ");
				throw new UserUnauthorizedException("El password no es correcto");
			}

			usuario.setContraseña(newPassword);
			userRepository.save(usuario);
			log.debug("Password cambiada con exito");

		} catch (DataAccessException e) {
			log.error("Error actualizando pass de usuario ", e);
			throw new UserUnauthorizedException("No autorizado");
		}

	}

	public List<RegistroPractica> consultarDetalles(Long idUser, LocalDate fechaInicio, LocalDate fechaFin)

			throws UserUnauthorizedException, UserNotFoundException {
		log.debug("Consultando detalles del usuario con id " + idUser);
		try {

			Optional<Usuario> alumnoOpt = userRepository.findById(idUser);

			if (!alumnoOpt.isPresent()) {
				log.warn("El usuario indicado no existe con id " + idUser);
				throw new UserNotFoundException("Usuario no encontrado");
			}
			Usuario usuario = alumnoOpt.get();

			if (fechaInicio == null) {

				fechaInicio = LocalDate.of(2024, 01, 01);

			}
			if (fechaFin == null) {

				fechaFin = LocalDate.of(2025, 03, 03);

			}

			List<RegistroPractica> lista = registroRepository.findByAlumno(usuario.getUsuarioAsociado());
			if (lista.isEmpty()) {
				log.warn("El usuario indicado no existe con id " + idUser);
				throw new UserNotFoundException("Usuario no encontrado");
			}

			return lista;
		} catch (DataAccessException e) {
			log.error("Error consultando detalles del usuario ", e);
			throw new UserUnauthorizedException("No autorizado");
		}
	}

	@Override
	public void crearRegistro(RegistroPractica registroPractica) throws UserUnauthorizedException {
		log.error("Creando el registro practica ");
		try {
			registroRepository.save(registroPractica);

		} catch (DataAccessException e) {
			log.error("Error al crear el registro practica ", e);
			throw new UserUnauthorizedException("No autorizado");
		}

	}

	@Override
	public void borrarRegistro(Long id) throws UserUnauthorizedException {
		log.error("Borrando el registro practica ");
		try {

			registroRepository.deleteById(id);

		} catch (DataAccessException e) {
			log.error("Error al borrar el registro practica ", e);
			throw new UserUnauthorizedException("No autorizado");
		}

	}

	@Override
	public List<FechaPractica> obtenerFechas(Long id, Integer anioCurso, String eva, LocalDate fechaDesde,
			LocalDate fechaHasta) throws UserUnauthorizedException {
		try {

			if (fechaDesde == null) {
				fechaDesde = LocalDate.of(2024, 01, 01);

			}
			if (fechaHasta == null) {
				fechaHasta = LocalDate.of(2025, 03, 03);

			}

			List<LocalDate> todasLasFechas = Stream.iterate(fechaDesde, date -> date.plusDays(1))
					.limit(ChronoUnit.DAYS.between(fechaDesde, fechaHasta) + 1).collect(Collectors.toList());

			List<FechaPractica> fechasEnBD = fechaRepository.findByAnioCursoAndEvaluacion(anioCurso, eva);
			Set<LocalDate> fechasRegistradas = fechasEnBD.stream().map(FechaPractica::getFecha)
					.collect(Collectors.toSet());

			List<LocalDate> fechasDisponibles = todasLasFechas.stream()
					.filter(fecha -> !fechasRegistradas.contains(fecha)).collect(Collectors.toList());

			List<FechaPractica> fechasPracticasDisponibles = fechasDisponibles.stream().map(fecha -> {
				FechaPractica nuevaFecha = new FechaPractica();
				nuevaFecha.setFecha(fecha);
				nuevaFecha.setAnioCurso(anioCurso);
				nuevaFecha.setEvaluacion(eva);

				return nuevaFecha;
			}).collect(Collectors.toList());

			return fechasPracticasDisponibles;
		} catch (DataAccessException e) {
			log.error("Error al obtener las fechas de práctica", e);
			throw new UserUnauthorizedException("No autorizado");
		}
	}

}
