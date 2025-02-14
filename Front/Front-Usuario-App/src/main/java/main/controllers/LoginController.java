package main.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.gui.AppController;


public class LoginController extends AppController {

    private Stage stage;  // Necesario para controlar la ventana

    // Método para inicializar el Stage, pasarlo al controlador
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // FXML de los campos de texto para el login
    @FXML
    private TextField textFieldContraseña;

    @FXML
    private TextField textFieldUsuario;

    // Método para minimizar la ventana
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
    void validar(ActionEvent event) {
        // Lógica de validación o acción para el login
        System.out.println("Validar presionado!");
        Platform.exit();  // Cierra la aplicación (como ejemplo)
    }
    


}
