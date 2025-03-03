package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuController extends AppController {
	private static final Logger log = LoggerFactory.getLogger(MenuController.class); // Logger para esta clase
	private Stage stage; // Necesario para controlar la ventana

	// Método para inicializar el Stage, pasarlo al controlador
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private ComboBox<String> comboBox;

	public void initialize() {
		// Añadir los elementos al ComboBox directamente
		comboBox.getItems().addAll("Cambiar Contraseña", "Ver datos", "Consultar Detalles de registros");

		// Establecer el evento cuando se selecciona una opción
		comboBox.setOnAction(this::cambiarEscenaDesdeComboBox);
		log.debug("ComboBox inicializado con opciones"); // Log de inicialización del ComboBox
	}

	// Método que se llama cuando se selecciona una opción en el ComboBox
	private void cambiarEscenaDesdeComboBox(ActionEvent event) {
		String seleccion = comboBox.getValue(); // Obtener la opción seleccionada
		log.debug("Opción seleccionada en el ComboBox: {}", seleccion); // Log de la opción seleccionada

		if (seleccion != null) {
			switch (seleccion) {
			case "Cambiar Contraseña":
				changeScene(FXML_CAMBIARCONTRASEÑA); // Ruta de la página para cambiar contraseña
				log.debug("Cambiando a la escena: Cambiar Contraseña"); // Log de cambio de escena
				break;
			case "Ver datos":
				changeScene(FXML_VERDATOS); // Ruta de la página para ver los datos
				log.debug("Cambiando a la escena: Ver Datos"); // Log de cambio de escena
				break;
			case "Consultar Detalles de registros":
				changeScene(FXML_CONSULTARREGISTRO); // Ruta de la página para consultar registros
				log.debug("Cambiando a la escena: Consultar Detalles de Registros"); // Log de cambio de escena
				break;
			default:
				log.warn("Selección no válida: {}", seleccion); // Log de advertencia para selección no válida
				break;
			}
		}
	}

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
	void cerrarSesion(ActionEvent event) {
		changeScene(FXML_LOGIN); // Cambia a la escena de login
		log.debug("Cierre de sesión y cambio a la escena de login"); // Log de cierre de sesión
	}
}
