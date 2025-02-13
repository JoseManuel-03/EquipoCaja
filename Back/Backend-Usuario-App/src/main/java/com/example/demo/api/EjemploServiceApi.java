package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.request.ChangePasswordRequest;
import com.example.demo.api.request.LoginRequest;
import com.example.demo.api.request.UsuarioDTO;
import com.example.demo.api.request.UsuarioDetalleDTO;
import com.example.demo.model.RegistroPractica;
import com.example.demo.model.Usuario;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Authorization")
public class EjemploServiceApi {

	@Autowired
	private UserService service;

	@PostMapping("/login")
    @Operation(summary = "Login de usuario", description = "Autentica al usuario y devuelve un token JWT")
	public UsuarioDTO login(@RequestBody @Valid LoginRequest request) {
		Usuario usuario = service.login(request.getUsername(),request.getPassword());
		UsuarioDTO dto = new UsuarioDTO(usuario, service.consultarDetalles(usuario.getId()));
		return dto;
	}

	@PutMapping
	@Operation(summary = "Cambia el pasword de User", description = "cambiael pasword")
	public void cambiarContraseña(@RequestBody @Valid ChangePasswordRequest request) {
		service.cambiarContraseña(request.getID, request.getOldPassword, request.newPassword);
	}

	@GetMapping
	@Operation(summary = "Consultar detalle user", description = "devuelve el alumno por id")
	public List<RegistroPractica> consultarDetalles(@PathVariable Long idUser) {
		return service.consultarDetalles(idUser);
	}

}
