package main.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.App;

public class AppController {
	public static final String FXML_LOGIN = "/fxml/login.fxml";
	public static final String FXML_MENU = "/fxml/menu.fxml";
	public static final String FXML_VERDATOS = "/fxml/verDatos.fxml";
	public static final String FXML_CAMBIARCONTRASEÑA = "/fxml/cambiarContraseña.fxml";
	public static final String FXML_CONSULTARREGISTRO = "/fxml/consultarRegistro.fxml";
	public static final String FXML_REGISTROHORAS = "/fxml/registroHoras.fxml";
	public static final String FXML_ALTAREGISTRO = "/fxml/altaRegistro.fxml";
	public static final String FXML_DETALLEREGISTRO = "/fxml/detalleRegistro.fxml";
	private static Stage primaryStage;

	
	public AppController() {
	}
	
	public AppController(Stage stage){
		primaryStage = stage;
	}

	public void changeScene(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(loader.load());

            // Asignamos el stage al controlador del FXML
            Object controller = loader.getController();
            if (controller instanceof LoginController) {
                LoginController loginController = (LoginController) controller;
                loginController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof MenuController) {
            	MenuController menuController = (MenuController) controller;
            	menuController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof ConsultarRegistroController) {
            	ConsultarRegistroController consultarRegistroController = (ConsultarRegistroController) controller;
                consultarRegistroController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof VerDatosController) {
            	VerDatosController verDatosController = (VerDatosController) controller;
                verDatosController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof CambiarContraseñaController) {
            	CambiarContraseñaController cambiarContraseñasController = (CambiarContraseñaController) controller;
                cambiarContraseñasController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof RegistroHorasController) {
            	RegistroHorasController registroHorasController = (RegistroHorasController) controller;
                registroHorasController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof AltaRegistroContorller) {
            	AltaRegistroContorller altaRegistroController = (AltaRegistroContorller) controller;
                altaRegistroController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof DetalleRegistroController) {
            	DetalleRegistroController detalleRegistroController = (DetalleRegistroController) controller;
                detalleRegistroController.setStage(primaryStage);  // Pasamos el Stage
            }
          
            primaryStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException("Error cambiando escena.", e);
        }
    }
	
	public Parent loadScene(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
			Scene scene = new Scene(loader.load());
			return scene.getRoot();
		}
		catch(IOException e) {
			throw new RuntimeException("Error cargando escena.", e);
		}
	}
	
	public void addParam(String key, Object param) {
		Map<String, Object> mapa = (Map<String, Object>) primaryStage.getUserData();
		if (mapa == null) {
			mapa = new HashMap<String, Object>();
			primaryStage.setUserData(mapa);
		}
		mapa.put(key, param);
	}
	
	public Object getParam(String key) {
		Map<String, Object> mapa = (Map<String, Object>) primaryStage.getUserData();
		return mapa.get(key);
	}
	/**
	 * Muestra una alerta con el mensaje de error.
	 *
	 * @param titulo  El título de la alerta.
	 * @param mensaje El mensaje de la alerta.
	 */
	public void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
	
}




