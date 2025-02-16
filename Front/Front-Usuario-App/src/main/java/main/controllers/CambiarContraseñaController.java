package main.controllers;
import org.openapitools.client.model.UsuarioDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.apiService.ApiService;
import main.gui.AppController;

public class CambiarContraseñaController extends AppController {

	private Stage stage; // Necesario para controlar la ventana

	// Método para inicializar el Stage, pasarlo al controlador
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private TextField textFieldAntigua;

	@FXML
	private TextField textFieldNueva;

	@FXML
	private Label textoError;

	private UsuarioDTO usuario; // Variable para almacenar el usuario

	@FXML
	void irMenu(ActionEvent event) {
		changeScene(FXML_MENU);

	}

	@FXML
	void minimizar(ActionEvent event) {
		if (stage != null) {
			stage.setIconified(true); // Minimiza la ventana
		}
	}

	// Método para cerrar la ventana
	@FXML
	void cerrar(ActionEvent event) {
		if (stage != null) {
			stage.close(); // Cierra la ventana
		}
	}

	@FXML
	void validar(ActionEvent event) {
		// Recupera el usuario almacenado en el AppController
		usuario = (UsuarioDTO) getParam("usuario");

		// Obtiene los valores de los campos de texto
		String contraseñaAntigua = textFieldAntigua.getText();
		String contraseñaNueva = textFieldNueva.getText();

		// Valida que los campos no estén vacíos
		if (contraseñaAntigua.isEmpty() || contraseñaNueva.isEmpty()) {
			textoError.setText("Error: Todos los campos son obligatorios.");
			return;
		}

		// Valida que la nueva contraseña tenga al menos 8 caracteres
		if (contraseñaNueva.length() < 8) {
			textoError.setText("Error: La nueva contraseña debe tener al menos 8 caracteres.");
			return;
		}

		// Llama al servicio de la API para cambiar la contraseña
		ApiService apiService = new ApiService();
		String resultado = apiService.cambiarContraseña(usuario.getId(), contraseñaAntigua, contraseñaNueva);

		// Muestra el resultado en la interfaz
		textoError.setText(resultado);

		textFieldAntigua.clear();
		textFieldNueva.clear();

	}
	
	public void initialize() {
		textoError.setText("");
	}

}
