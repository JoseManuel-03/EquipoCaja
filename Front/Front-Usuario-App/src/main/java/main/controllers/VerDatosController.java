package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.gui.AppController;

public class VerDatosController extends AppController {

	
	private Stage stage;  // Necesario para controlar la ventana

    // Método para inicializar el Stage, pasarlo al controlador
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private Label labelActivoUsuario;

    @FXML
    private Label labelAñoCursoAlumno;

    @FXML
    private Label labelCicloAlumno;

    @FXML
    private Label labelEmpresaAsignadaALumno;

    @FXML
    private Label labelEvaluacionAlumno;

    @FXML
    private Label labelNombreALumno;

    @FXML
    private Label labelNombreUsuario;

    @FXML
    private Label labelPerfilUsuario;

    @FXML
    private Label labelTutorDocenteAlumno;

  

    @FXML
    void irMenu(ActionEvent event) {
    	changeScene(FXML_MENU);

    }

    @FXML
    void irRegistroHoras(ActionEvent event) {
    	changeScene(FXML_REGISTROHORAS);
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

    
    public void initialize() {
    	
    }

}
