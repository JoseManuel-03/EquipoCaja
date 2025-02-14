package com.example.demo.api;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.request.ChangePasswordRequest;
import com.example.demo.api.request.LoginRequest;
import com.example.demo.api.request.UsuarioDTO;
import com.example.demo.model.RegistroPractica;
import com.example.demo.model.Usuario;
import com.example.demo.service.UserNotFoundException;
import com.example.demo.service.UserService;
import com.example.demo.service.UserUnauthorizedException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Authorization")
public class UsuarioServiceApi {

	@Autowired
	private UserService service;

	@PostMapping("/login")
	@Operation(summary = "Login de usuario", description = "Autentica al usuario y devuelve un token JWT")
	public ResponseEntity<UsuarioDTO> login(@RequestBody @Valid LoginRequest request)
			throws UserNotFoundException, UserUnauthorizedException {
		Usuario usuario = service.login(request.getUsername(), request.getPassword());
		List<RegistroPractica> registros = service.consultarDetalles(usuario.getId(), null, null);
		return ResponseEntity.ok(new UsuarioDTO(usuario, registros));
	}

	@PutMapping
	@Operation(summary = "Cambia el pasword de User", description = "cambiael pasword")
	public ResponseEntity<String> cambiarContraseña(@RequestBody @Valid ChangePasswordRequest request)
			throws UserUnauthorizedException, UserNotFoundException {
		service.cambiarContraseña(request.getId(), request.getOldPassword(), request.getNewPassword());
		return ResponseEntity.ok("Contaseña cambiada");
	}

	@GetMapping("/{idUser}")
	@Operation(summary = "Consultar detalle user", description = "Devuelve el detalle del usuario por ID")
	public ResponseEntity<List<RegistroPractica>> consultarDetalles(@PathVariable Long idUser, LocalDate fecha1,
			LocalDate fecha2) throws UserUnauthorizedException, UserNotFoundException {
		return ResponseEntity.ok(
				Optional.ofNullable(service.consultarDetalles(idUser, fecha1, fecha2)).orElse(Collections.emptyList()));
	}

	@PostMapping("/registro")
	@Operation(summary = "Crear Registro", description = "Crea un nuevo registro de práctica.")
	public ResponseEntity<String> crearRegistro(@RequestBody @Valid RegistroPractica registro) {
		service.crearRegistro(registro);
		return ResponseEntity.ok("Registro creado exitosamente");
	}

	@DeleteMapping("/registro/{id}")
	@Operation(summary = "Borrar Registro", description = "Elimina un registro de práctica por ID.")
	public ResponseEntity<String> borrarRegistro(@PathVariable Long id) {
		service.borrarRegistro(id);
		return ResponseEntity.ok("Registro eliminado exitosamente");
	}

}
