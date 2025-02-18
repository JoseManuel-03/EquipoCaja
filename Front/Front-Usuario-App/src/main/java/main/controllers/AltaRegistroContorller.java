package main.controllers;

import java.time.LocalDate;
import java.util.List;

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
import main.gui.AppController;

public class AltaRegistroContorller extends AppController {
 
	    private Stage stage;  // Necesario para controlar la ventana

	    @FXML
	    private TextArea textAreaDetalleRegistro;

	    @FXML
	    private TextField textFieldHoras;

	    @FXML
	    private ComboBox<LocalDate> comboBoxFechasDisponibles;

	    private ApiService apiService = new ApiService(); // Instancia del servicio de API

	    // Método para inicializar el Stage, pasarlo al controlador
	    public void setStage(Stage stage) {
	        this.stage = stage;
	    }

	    @FXML
	    public void initialize() {
	        // Obtén el usuario logueado desde el AppController
	        UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");

	        if (usuario != null) {
	            // Obtén las fechas disponibles para el alumno
	            List<LocalDate> fechasDisponibles = apiService.consultarDetalles(null, null, null);

	            // Configura el ComboBox con las fechas disponibles
	            if (fechasDisponibles != null && !fechasDisponibles.isEmpty()) {
	                comboBoxFechasDisponibles.getItems().addAll(fechasDisponibles);
	                comboBoxFechasDisponibles.setValue(fechasDisponibles.get(0)); // Selecciona la primera fecha por defecto
	            } else {
	                mostrarAlerta("Error", "No hay fechas disponibles para registrar horas.");
	            }
	        } else {
	            mostrarAlerta("Error", "No se ha iniciado sesión.");
	        }
	    }

	    @FXML
	    void guardarRegistro(ActionEvent event) {
	        // Obtén los datos ingresados por el usuario
	        LocalDate fechaSeleccionada = comboBoxFechasDisponibles.getValue();
	        String horasTexto = textFieldHoras.getText();
	        String detalle = textAreaDetalleRegistro.getText();

	        // Valida los datos
	        if (fechaSeleccionada == null) {
	            mostrarAlerta("Error", "Debes seleccionar una fecha.");
	            return;
	        }

	        if (horasTexto.isEmpty() || detalle.isEmpty()) {
	            mostrarAlerta("Error", "Todos los campos son obligatorios.");
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

	        // Crea el objeto RegistroPractica
	        RegistroPractica registro = new RegistroPractica();
	        registro.setFecha(fechaSeleccionada);
	        registro.setCantidadHoras(horas);
	        registro.setDescripcion(detalle);

	        // Llama al servicio de la API para guardar el registro
	        String resultado = apiService.crearRegistro(registro);

	        if (resultado != null && resultado.contains("éxito")) {
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
	            stage.setIconified(true);  // Minimiza la ventana
	        }
	    }

	    // Método para cerrar la ventana
	    @FXML
	    void cerrar(ActionEvent event) {
	        if (stage != null) {
	            stage.close();  // Cierra la ventana
	        }
	    }

	    /**
	     * Obtiene las fechas disponibles para el alumno.
	     *
	     * @param idUsuario El ID del usuario.
	     * @return Lista de fechas disponibles.
	     
	    private List<LocalDate> obtenerFechasDisponibles(Long idUsuario) {
	        // Llama al servicio de la API para obtener las fechas disponibles
	        // Este método debe ser implementado en ApiService
	        return apiService.obtenerFechasDisponibles(idUsuario);
	    }
	     */
	    /**
	     * Muestra una alerta con el mensaje de error.
	     *
	     * @param titulo  El título de la alerta.
	     * @param mensaje El mensaje de la alerta.
	     */
	    private void mostrarAlerta(String titulo, String mensaje) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(titulo);
	        alert.setHeaderText(null);
	        alert.setContentText(mensaje);
	        alert.showAndWait();
	    }
	}
