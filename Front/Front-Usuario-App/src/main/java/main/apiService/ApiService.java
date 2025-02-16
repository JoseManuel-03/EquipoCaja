package main.apiService;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioServiceApiApi;
import org.openapitools.client.model.ChangePasswordRequest;
import org.openapitools.client.model.LoginRequest;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.UsuarioDTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

public class ApiService {

    private UsuarioServiceApiApi service;

    public ApiService() {
        ApiClient cliente = new ApiClient();
        cliente.setBasePath("http://localhost:8080"); 
        cliente.setApiKey("blaspass"); 
        this.service = new UsuarioServiceApiApi(cliente);
    }

    /**
     * Realiza el login de un usuario.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return UsuarioDTO con los detalles del usuario, o null si ocurre un error.
     */
    public UsuarioDTO login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(hashPassword(password)); // Cifra la contraseña antes de enviarla

        try {
            UsuarioDTO usuarioDTO = service.login(loginRequest);
            if (usuarioDTO != null /*&& usuarioDTO.getActivo == true*/) {
                return usuarioDTO; // Solo permite el login si el usuario está activo y es un alumno
            } else {
                return null; // Usuario inactivo o no es un alumno
            }
        } catch (ApiException e) {
            e.printStackTrace();
            // Manejar el error, por ejemplo, mostrar un mensaje al usuario
            return null;
        }
    }

    /**
     * Cifra una contraseña usando el algoritmo SHA-256.
     *
     * @param password Contraseña en texto plano.
     * @return Contraseña cifrada en formato hexadecimal.
     */
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cifrar la contraseña", e);
        }
    }


    /**
     * Cambia la contraseña de un usuario.
     *
     * @param id el id.
     * @param oldPassword Contraseña actual.
     * @param newPassword Nueva contraseña.
     * @return Mensaje de confirmación, o null si ocurre un error.
     */
public String cambiarContraseña(Long id, String oldPassword, String newPassword) {
    // Cifra las contraseñas antes de enviarlas al servidor
    String hashedOldPassword = hashPassword(oldPassword);
    String hashedNewPassword = hashPassword(newPassword);

    // Crea la solicitud para cambiar la contraseña
    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
    changePasswordRequest.setId(id);
    changePasswordRequest.setOldPassword(hashedOldPassword); 
    changePasswordRequest.setNewPassword(hashedNewPassword); 

    try {
        // Llama al servicio de la API para cambiar la contraseña
        return service.cambiarContrasea(changePasswordRequest);
    } catch (ApiException e) {
        e.printStackTrace();
        // Manejar el error
        return null;
    }
}

    /**
     * Obtiene los registros de práctica de un usuario en un rango de fechas.
     *
     * @param idUser ID del usuario.
     * @param fecha1 Fecha de inicio (opcional).
     * @param fecha2 Fecha de fin (opcional).
     * @return Lista de registros de práctica, o null si ocurre un error.
     */
    public List<RegistroPractica> consultarDetalles(Long idUser, LocalDate fecha1, LocalDate fecha2) {
        try {
            return service.consultarDetalles(idUser, fecha1, fecha2);
        } catch (ApiException e) {
            e.printStackTrace();
            // Manejar el error
            return null;
        }
    }

    /**
     * Crea un nuevo registro de práctica.
     *
     * @param registroPractica Datos del registro de práctica.
     * @return Mensaje de confirmación, o null si ocurre un error.
     */
    public String crearRegistro(RegistroPractica registroPractica) {
        try {
            return service.crearRegistro(registroPractica);
        } catch (ApiException e) {
            e.printStackTrace();
            // Manejar el error
            return null;
        }
    }

    /**
     * Elimina un registro de práctica por su ID.
     *
     * @param id ID del registro de práctica.
     * @return Mensaje de confirmación, o null si ocurre un error.
     */
    public String borrarRegistro(Long id) {
        try {
            return service.borrarRegistro(id);
        } catch (ApiException e) {
            e.getMessage();
         
            return null;
        }
    }
}