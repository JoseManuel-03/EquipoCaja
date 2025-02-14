package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.gui.AppController;

public class AltaRegistroContorller extends AppController {

	 private Stage stage;  // Necesario para controlar la ventana

	    // Método para inicializar el Stage, pasarlo al controlador
	    public void setStage(Stage stage) {
	        this.stage = stage;
	    }
    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea textAreaDetalleRegistro;

    @FXML
    private TextField textFieldHoras;



    @FXML
    void guardarRegistro(ActionEvent event) {

    }

    @FXML
    void irMenu(ActionEvent event) {
    	changeScene(FXML_MENU);
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

