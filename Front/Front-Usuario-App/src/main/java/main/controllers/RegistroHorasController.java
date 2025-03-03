package main.controllers;

import java.util.List;

import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.UsuarioDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.apiService.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistroHorasController extends AppController {
	private static final Logger log = LoggerFactory.getLogger(RegistroHorasController.class); // Logger para esta clase
	private Stage stage; // Necesario para controlar la ventana

	// Método para inicializar el Stage, pasarlo al controlador
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private Label labelHorasPendientesRealizar;

	@FXML
	private Label labelHorasTotal;

	@FXML
	private Label labelPorcentajeSobreTotal;

	@FXML
	private Label labelHorasRealizadas;

	private ApiService apiService = new ApiService(); // Instancia del servicio de API

	@FXML
	void irMenu(ActionEvent event) {
		changeScene(FXML_VERDATOS);
		log.debug("Navegando al menú de datos"); // Log de navegación
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
	public void initialize() {
		mostrarResumenPracticas(); // Muestra el resumen al inicializar
		log.debug("Inicialización completa y resumen de prácticas mostrado"); // Log de inicialización
	}

	/**
	 * Calcula y muestra el resumen de prácticas del alumno.
	 *
	 * @param idUsuario El ID del usuario.
	 */
	private void mostrarResumenPracticas() {
		// Obtén los registros de prácticas del alumno
		UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");

		List<RegistroPractica> registros = apiService.consultarDetalles(usuario.getId(), null, null);
		log.debug("Registros de prácticas consultados para el usuario ID: {}", usuario.getId()); // Log de consulta

		if (registros == null) {
			mostrarAlerta("Error", "No se pudieron cargar los registros de prácticas.");
			log.error("No se pudieron cargar los registros de prácticas para el usuario ID: {}", usuario.getId()); // Log
																													// de
																													// error
			return;
		}

		// Calcula las horas totales, realizadas y pendientes
		double horasTotales = 370; // Supongamos que el total de horas requeridas es 370
		double horasRealizadas = 0;

		for (RegistroPractica registro : registros) {
			horasRealizadas += registro.getCantidadHoras();
		}

		double porcentajeHoras = (horasRealizadas / horasTotales) * 100;
		double horasPendientes = horasTotales - horasRealizadas;

		// Muestra el resumen en los labels
		labelHorasTotal.setText(String.valueOf(horasTotales));
		labelHorasRealizadas.setText(String.valueOf(horasRealizadas));
		labelPorcentajeSobreTotal.setText(String.format("%.2f%%", porcentajeHoras));
		labelHorasPendientesRealizar.setText(String.valueOf(horasPendientes));

		log.debug("Resumen de prácticas mostrado: Total: {}, Realizadas: {}, Porcentaje: {:.2f}%, Pendientes: {}",
				horasTotales, horasRealizadas, porcentajeHoras, horasPendientes); // Log del resumen mostrado
	}
}
