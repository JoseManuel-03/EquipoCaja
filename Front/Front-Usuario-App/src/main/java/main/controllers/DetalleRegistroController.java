package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.apiService.ApiService;
import main.gui.AppController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import org.openapitools.client.model.RegistroPractica;

public class DetalleRegistroController extends AppController {
	private Stage stage;  // Necesario para controlar la ventana

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
            labelFecha.setText(registro.getFecha().toString());
            labelHoras.setText(String.valueOf(registro.getCantidadHoras()));
            labelDescripcion.setText(registro.getDescripcion());
        }

        // Configura el botón de borrar
        botonBorrar.setOnAction(event -> borrarRegistro());
    }

    private void borrarRegistro() {
        // Llama al servicio de la API para borrar el registro
        String resultado = apiService.borrarRegistro(registro.getId());

        // Muestra un mensaje de confirmación
        if (resultado != null/* && resultado.contains("éxito")*/) {
        	 changeScene(FXML_CONSULTARREGISTRO);
            
        } else {
            // Muestra un mensaje de error
            mostrarAlerta("Error", "No se pudo borrar el registro.");
        }
       
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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
}