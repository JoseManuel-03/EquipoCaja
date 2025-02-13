package com.example.demo.api;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public UsuarioDTO login(@RequestBody @Valid LoginRequest request)
	        throws UserNotFoundException, UserUnauthorizedException {
	    Usuario usuario = service.login(request.getUsername(), request.getPassword());
	    List<RegistroPractica> registros = service.consultarDetalles(usuario.getId());
	    return new UsuarioDTO(usuario, registros);
	}


	@PutMapping
	@Operation(summary = "Cambia el pasword de User", description = "cambiael pasword")
	public void cambiarContraseña(@RequestBody @Valid ChangePasswordRequest request)
			throws UserUnauthorizedException, UserNotFoundException {
		service.cambiarContraseña(request.getId(), request.getOldPassword(), request.getNewPassword());
	}

	@GetMapping("/{idUser}")
	@Operation(summary = "Consultar detalle user", description = "Devuelve el detalle del usuario por ID")
	public List<RegistroPractica> consultarDetalles(@PathVariable Long idUser)
	        throws UserUnauthorizedException, UserNotFoundException {
	    return Optional.ofNullable(service.consultarDetalles(idUser))
	                   .orElse(Collections.emptyList());
	}


}
