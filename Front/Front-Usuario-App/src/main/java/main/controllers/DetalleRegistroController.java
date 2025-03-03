package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.apiService.ApiService;
import javafx.scene.control.Button;
import org.openapitools.client.model.RegistroPractica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DetalleRegistroController extends AppController {
	private static final Logger log = LoggerFactory.getLogger(DetalleRegistroController.class); // Logger para esta
																								// clase
	private Stage stage; // Necesario para controlar la ventana

	// Método para inicializar el Stage, pasarlo al controlador
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private Label labelFecha;

	@FXML
	private Label labelHoras;

	@FXML
	private Label labelDescripcion;

	@FXML
	private Button botonBorrar;

	private RegistroPractica registro;
	private ApiService apiService = new ApiService(); // Instancia del servicio de API

	@FXML
	public void initialize() {
		// Recupera el registro seleccionado desde el AppController
		registro = (RegistroPractica) getParam("registroSeleccionado");

		// Muestra los detalles del registro
		if (registro != null) {
			if (registro.getFecha() != null && registro.getFecha().getFecha() != null) {
				labelFecha.setText(registro.getFecha().getFecha().toString());
			} else {
				labelFecha.setText("Sin fecha");
			}

			labelHoras.setText(String.valueOf(registro.getCantidadHoras()));
			labelDescripcion.setText(registro.getDescripcion());
			log.debug("Detalles del registro cargados: Fecha: {}, Horas: {}, Descripción: {}", labelFecha.getText(),
					labelHoras.getText(), labelDescripcion.getText()); // Log de los detalles cargados
		}

		// Configura el botón de borrar
		botonBorrar.setOnAction(event -> borrarRegistro());
	}

	private void borrarRegistro() {
		// Llama al servicio de la API para borrar el registro
		String resultado = apiService.borrarRegistro(registro.getId());
		log.debug("Intento de borrar el registro con ID: {}", registro.getId()); // Log del intento de borrado
		System.out.println(resultado);

		// Muestra un mensaje de confirmación
		if (resultado != null && resultado.contains("exitosamente")) {
			mostrarMensajeExito(resultado);
			changeScene(FXML_CONSULTARREGISTRO);
			log.debug("Registro borrado exitosamente: {}", registro.getId()); // Log del borrado exitoso
		} else {
			// Muestra un mensaje de error
			mostrarAlerta("Error", "No se pudo borrar el registro.");
			log.warn("Error al borrar el registro con ID: {}", registro.getId()); // Log de advertencia en caso de error
		}
	}

	@FXML
	void irMenu(ActionEvent event) {
		changeScene(FXML_CONSULTARREGISTRO);
		log.debug("Navegando al menú de consulta de registros"); // Log de navegación
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
}
