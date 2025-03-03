package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class MenuController extends AppController {

	 private Stage stage;  // Necesario para controlar la ventana

	    // Método para inicializar el Stage, pasarlo al controlador
	    public void setStage(Stage stage) {
	        this.stage = stage;
	    }

	@FXML
    private ComboBox<String> comboBox;

    public void initialize() {
        // Añadir los elementos al ComboBox directamente
        comboBox.getItems().addAll(
            "Cambiar Contraseña",
            "Ver datos",
            "Consultar Detalles de registros"
        );

        // Establecer el evento cuando se selecciona una opción
        comboBox.setOnAction(this::cambiarEscenaDesdeComboBox);
    }

    // Método que se llama cuando se selecciona una opción en el ComboBox
    private void cambiarEscenaDesdeComboBox(ActionEvent event) {
        String seleccion = comboBox.getValue(); // Obtener la opción seleccionada

        if (seleccion != null) {
            switch (seleccion) {
                case "Cambiar Contraseña":
                    changeScene(FXML_CAMBIARCONTRASEÑA);  // Ruta de la página para cambiar contraseña
                    break;
                case "Ver datos":
                    changeScene(FXML_VERDATOS);  // Ruta de la página para ver los datos
                    break;
                case "Consultar Detalles de registros":
                    changeScene(FXML_CONSULTARREGISTRO);  // Ruta de la página para consultar registros
                    break;
                default:
                    break;
            }
        }
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
    
    @FXML
    void cerrarSesion(ActionEvent event) {
    	changeScene(FXML_LOGIN);
    }


}
