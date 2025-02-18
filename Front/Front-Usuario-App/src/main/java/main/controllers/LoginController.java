	package main.controllers;

import org.openapitools.client.model.UsuarioDTO;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.apiService.*;
import main.gui.AppController;


public class LoginController extends AppController {

    private Stage stage;  // Necesario para controlar la ventana

    // Método para inicializar el Stage, pasarlo al controlador
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // FXML de los campos de texto para el login
    @FXML
    private PasswordField textFieldContraseña;

    @FXML
    private TextField textFieldUsuario;
    
    @FXML
    private Label textoError;
    
    private ApiService apiService;

    public LoginController() {
        this.apiService = new ApiService(); // Inicializa el servicio de API
    }

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
    	String username = textFieldUsuario.getText();
        String password = textFieldContraseña.getText();

        UsuarioDTO usuarioDTO = apiService.login(username, password);
        if (usuarioDTO != null) {
            textoError.setText("Login exitoso! Bienvenido, " + usuarioDTO.getNombreCompleto());
            addParam("usuario", usuarioDTO);
            changeScene(FXML_MENU);
        } else {
            textoError.setText("Error en el login. Verifica tus credenciales.");
        }
    }
    


}
