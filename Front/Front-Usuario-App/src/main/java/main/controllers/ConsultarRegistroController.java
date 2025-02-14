package main.controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import main.gui.AppController;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ConsultarRegistroController extends AppController {

	 private Stage stage;  // Necesario para controlar la ventana

	    // Método para inicializar el Stage, pasarlo al controlador
	    public void setStage(Stage stage) {
	        this.stage = stage;
	    }
    @FXML
    private TableColumn<?, ?> ColumnaFechas;

    @FXML
    private TableColumn<?, ?> columnaDescripcion;

    @FXML
    private TableColumn<?, ?> columnaHorasRegistradas;

    @FXML
    private ComboBox<?> comoboBox;

    @FXML
    private DatePicker datePickerDesde;

    @FXML
    private DatePicker datePickerHasta;

    @FXML
    private TableView<?> tabla;

    @FXML
    void altaRegistro(ActionEvent event) {
    	changeScene(FXML_ALTAREGISTRO);
    }


    @FXML
    void consultarEntreFechas(ActionEvent event) {

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
