package main.controllers;

import org.openapitools.client.model.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.apiService.*;

public class LoginController extends AppController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	private Stage stage; // Necesario para controlar la ventana

	// Método para inicializar el Stage, pasarlo al controlador
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// FXML de los campos de texto para el login
	@FXML
	private PasswordField textFieldContraseña;

	@FXML
	private TextField textFieldUsuario;

	@FXML
	private Label textoError;

	private ApiService apiService;

	public LoginController() {
		this.apiService = new ApiService(); // Inicializa el servicio de API
	}

	// Método para minimizar la ventana
	@FXML
	void minimizar(ActionEvent event) {
		if (stage != null) {
			stage.setIconified(true); // Minimiza la ventana
			log.debug("Ventana minimizada"); // Log de la acción
		}
	}

	// Método para cerrar la ventana
	@FXML
	void cerrar(ActionEvent event) {
		if (stage != null) {
			stage.close(); // Cierra la ventana
			log.debug("Ventana cerrada"); // Log de la acción
		}
	}

	@FXML
	void validar(ActionEvent event) {
		String username = textFieldUsuario.getText();
		String password = textFieldContraseña.getText();

		log.debug("Intento de login con usuario: {}", username); // Log del intento de login

		UsuarioDTO usuarioDTO = apiService.login(username, password);
		if (usuarioDTO != null) {
			textoError.setText("Login exitoso! Bienvenido, " + usuarioDTO.getNombreUsuario());
			addParam("usuario", usuarioDTO);
			addParam("nombre", textFieldUsuario.getText());
			changeScene(FXML_MENU);
			log.debug("Login exitoso para usuario: {}", usuarioDTO.getNombreUsuario()); // Log del login exitoso
		} else {
			textoError.setText("Error en el login. Verifica tus credenciales.");
			log.warn("Error en el login para usuario: {}", username); // Log de advertencia en caso de error
		}
	}
}
