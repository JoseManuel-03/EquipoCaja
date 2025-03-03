package main.controllers;

import java.time.LocalDate;

import java.util.List;

import org.openapitools.client.model.FechaPractica;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.UsuarioDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.apiService.ApiService;

public class AltaRegistroContorller extends AppController {

	private Stage stage; // Necesario para controlar la ventana

	@FXML
	private TextArea textAreaDetalleRegistro;

	@FXML
	private TextField textFieldHoras;

	@FXML
	private ComboBox<LocalDate> comboBoxFechasDisponibles;

	private ApiService apiService = new ApiService(); // Instancia del servicio de API
	
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;

	// Método para inicializar el Stage, pasarlo al controlador
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void initialize() {
		// Obtén el usuario logueado desde el AppController
		UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");
		 fechaDesde = (LocalDate) getParam("fechaDesde");
		 fechaHasta = (LocalDate) getParam("fechaHasta");
		if (usuario != null) {
			// Obtén las fechas disponibles para el alumno
			
			List<FechaPractica> fechasDisponibles = apiService.obtenerFechasDisponibles(usuario.getId(),
					usuario.getUsuarioAsociado().getAnioCurso(),
					usuario.getUsuarioAsociado().getEvaluacion(),
					fechaDesde,
					fechaHasta);
		
			// Configura el ComboBox con solo las fechas
			if (fechasDisponibles != null && !fechasDisponibles.isEmpty()) {
			    List<LocalDate> fechas = fechasDisponibles.stream()
			            .map(FechaPractica::getFecha) // Extrae solo la fecha
			            .toList();

			    comboBoxFechasDisponibles.getItems().addAll(fechas);
			    comboBoxFechasDisponibles.setValue(fechas.get(0)); // Selecciona la primera fecha por defecto
			} else {
				mostrarAlerta("Error", "No hay fechas disponibles para registrar horas.");
			}
		} else {
			mostrarAlerta("Error", "No se ha iniciado sesión.");
		}
	}

	@FXML
	void guardarRegistro(ActionEvent event) {
	    // Obtén la fecha seleccionada en el ComboBox
	    LocalDate fechaSeleccionada = comboBoxFechasDisponibles.getValue();
	    if (fechaSeleccionada == null) {
	        mostrarAlerta("Error", "Debes seleccionar una fecha.");
	        return;
	    }

	    // Obtén el usuario logueado
	    UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");
	    if (usuario == null) {
	        mostrarAlerta("Error", "No se ha iniciado sesión.");
	        return;
	    }

	    // Buscar el objeto FechaPractica correspondiente a la fecha seleccionada
	    FechaPractica fechaPracticaSeleccionada = apiService.obtenerFechasDisponibles(usuario.getId(),
				usuario.getUsuarioAsociado().getAnioCurso(),
				usuario.getUsuarioAsociado().getEvaluacion(),
				fechaDesde,
				fechaHasta)
	            .stream()
	            .filter(fp -> fp.getFecha().equals(fechaSeleccionada))
	            .findFirst()
	            .orElse(null);

	    if (fechaPracticaSeleccionada == null) {
	        mostrarAlerta("Error", "No se encontró la fecha seleccionada.");
	        return;
	    }

	    // Obtener y validar la cantidad de horas
	    String horasTexto = textFieldHoras.getText();
	    if (horasTexto.isEmpty()) {
	        mostrarAlerta("Error", "Debes ingresar la cantidad de horas.");
	        return;
	    }

	    double horas;
	    try {
	        horas = Double.parseDouble(horasTexto);
	    } catch (NumberFormatException e) {
	        mostrarAlerta("Error", "Las horas deben ser un número decimal.");
	        return;
	    }

	    if (horas <= 0 || horas > 8 || (horas * 2) % 1 != 0) {
	        mostrarAlerta("Error", "Las horas deben ser un número decimal con saltos de 0.5, mayor a 0 y menor o igual a 8.");
	        return;
	    }

	    // Obtener y validar el detalle
	    String detalle = textAreaDetalleRegistro.getText();
	    if (detalle.isEmpty()) {
	        mostrarAlerta("Error", "Debes ingresar un detalle.");
	        return;
	    }

	    // Crear el objeto RegistroPractica con la fecha correcta
	    
	    RegistroPractica registro = new RegistroPractica();
	    registro.setFecha(fechaPracticaSeleccionada); // Se asigna la fechaPractica correcta
	    registro.setCantidadHoras(horas);
	    registro.setDescripcion(detalle);
	    registro.setAlumno(usuario.getUsuarioAsociado());
	    

	    // Llamar al servicio de la API para guardar el registro
	    String resultado = apiService.crearRegistro(registro);
	    

	    if (resultado != null && resultado.contains("exitosamente")) {
	        mostrarAlerta("Éxito", "Registro guardado correctamente.");
	        changeScene(FXML_CONSULTARREGISTRO); // Vuelve a la pantalla de consulta
	    } else {
	        mostrarAlerta("Error", "No se pudo guardar el registro.");
	    }
	}


	@FXML
	void irMenu(ActionEvent event) {
		changeScene(FXML_CONSULTARREGISTRO);
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

	

}
