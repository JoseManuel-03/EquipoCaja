package com.example.demo;

import org.junit.jupiter.api.Test;


import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.UserNotFoundException;
import com.example.demo.service.UserService;
import com.example.demo.service.UserUnauthorizedException;
import com.example.demo.model.Usuario;
import com.example.demo.model.RegistroPractica;
import com.example.demo.model.FechaPractica;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class Apps {

	@Autowired
	private UserService userService;

	private Usuario testUser;

	@BeforeEach
	void setUp() {
		testUser = new Usuario();
		testUser.setNombreUsuario("juanp");
		testUser.setContraseña("ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb");
	}

	@Test
	void testLoginSuccess() throws UserNotFoundException, UserUnauthorizedException {
		Usuario user = userService.login("juanp", "ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb");
		assertNotNull(user);
		assertEquals("testUser", user.getNombreUsuario());
	}

	@Test
	void testLoginUserNotFound() {
		assertThrows(UserNotFoundException.class, () -> {
			userService.login("nonExistentUser", "password123");
		});
	}

	@Test
	void testLoginWrongPassword() {
		assertThrows(UserUnauthorizedException.class, () -> {
			userService.login("juanp", "wrongPassword");
		});
	}

	@Test
	void testChangePasswordSuccess() throws UserNotFoundException, UserUnauthorizedException {
		userService.cambiarContraseña(testUser.getId(),
				"ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb", "newPassword456");
		Usuario updatedUser = userService.login("testUser", "newPassword456");
		assertNotNull(updatedUser);
	}

	@Test
	void testChangePasswordFailsOldPasswordIncorrect() {
		assertThrows(UserUnauthorizedException.class, () -> {
			userService.cambiarContraseña(testUser.getId(), "wrongPassword", "newPassword456");
		});
	}

	@Test
	void testChangePasswordFailsSamePassword() {
		assertThrows(UserUnauthorizedException.class, () -> {
			userService.cambiarContraseña(testUser.getId(), "newPassword456", "newPassword456");
		});
	}

	@Test
	void testConsultarDetallesSuccess() throws UserUnauthorizedException, UserNotFoundException {
		List<RegistroPractica> registros = userService.consultarDetalles(testUser.getId(), LocalDate.of(2024, 1, 1),
				LocalDate.of(2024, 12, 31));
		assertNotNull(registros);
	}

	@Test
	void testCrearRegistroSuccess() throws UserUnauthorizedException {
		RegistroPractica registro = new RegistroPractica();
		userService.crearRegistro(registro);
	}

	@Test
	void testBorrarRegistroSuccess() throws UserUnauthorizedException {
		userService.borrarRegistro(1L);
	}

	@Test
	void testObtenerFechasSuccess() throws UserUnauthorizedException {
		//List<FechaPractica> fechas = userService.obtenerFechas(testUser.getId(), 2024, "Evaluacion");
		//assertNotNull(fechas);
	}
}
