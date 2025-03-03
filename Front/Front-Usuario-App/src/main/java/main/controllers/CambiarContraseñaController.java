package main.controllers;

import org.openapitools.client.model.UsuarioDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import main.apiService.ApiService;

public class CambiarContraseñaController extends AppController {

    private static final Logger log = LoggerFactory.getLogger(CambiarContraseñaController.class);
    private Stage stage; // Necesario para controlar la ventana

    // Método para inicializar el Stage, pasarlo al controlador
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private PasswordField textFieldAntigua;

    @FXML
    private PasswordField textFieldNueva;

    @FXML
    private Label textoError;

    private UsuarioDTO usuario; // Variable para almacenar el usuario

    @FXML
    void irMenu(ActionEvent event) {
        log.debug("Navegando al menú principal");
        changeScene(FXML_MENU);
    }

    @FXML
    void minimizar(ActionEvent event) {
        if (stage != null) {
            stage.setIconified(true); // Minimiza la ventana
            log.debug("Ventana minimizada");
        }
    }

    // Método para cerrar la ventana
    @FXML
    void cerrar(ActionEvent event) {
        if (stage != null) {
            stage.close(); // Cierra la ventana
            log.debug("Ventana cerrada");
        }
    }

    @FXML
    void validar(ActionEvent event) {
        usuario = (UsuarioDTO) getParam("usuario");
        
        if (usuario == null) {
            log.warn("Intento de cambio de contraseña sin usuario logueado");
            mostrarAlerta("Error", "No se ha iniciado sesión.");
            return;
        }

        String contraseñaAntigua = textFieldAntigua.getText();
        String contraseñaNueva = textFieldNueva.getText();

        log.debug("Intento de cambio de contraseña para usuario: {}", usuario.getId());

        if (contraseñaAntigua.equals(contraseñaNueva)) {
            log.warn("Contraseña nueva es igual a la antigua");
            mostrarAlerta("Contraseña igual", "La contraseña nueva tiene que ser diferente a la antigua");
            return;
        }

        if (contraseñaAntigua.isEmpty() || contraseñaNueva.isEmpty()) {
            log.warn("Campos vacíos en cambio de contraseña");
            textoError.setText("Error: Todos los campos son obligatorios.");
            return;
        }

        if (contraseñaNueva.length() < 8) {
            log.warn("Intento de cambio de contraseña con menos de 8 caracteres");
            textoError.setText("Error: La nueva contraseña debe tener al menos 8 caracteres.");
            return;
        }

        ApiService apiService = new ApiService();
        String resultado = apiService.cambiarContraseña(usuario.getId(), contraseñaAntigua, contraseñaNueva);

        if (resultado != null && resultado.contains("cambiada")) {
            log.debug("Contraseña cambiada exitosamente para usuario: {}", usuario.getId());
            mostrarMensajeExito("Contraseña cambiada exitosamente");
        } else {
            log.error("Error al cambiar la contraseña para usuario: {}", usuario.getId());
            textoError.setText("Acceso incorrecto");
        }

        textFieldAntigua.clear();
        textFieldNueva.clear();
    }

    public void initialize() {
        textoError.setText("");
        log.debug("Inicializando CambiarContraseñaController");
    }
}
