package main.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.App;
import main.controllers.CambiarContraseñaController;
import main.controllers.ConsultarRegistroController;
import main.controllers.LoginController;
import main.controllers.MenuController;
import main.controllers.RegistroHorasController;
import main.controllers.VerDatosController;

public class AppController {
	public static final String FXML_LOGIN = "/fxml/login.fxml";
	public static final String FXML_MENU = "/fxml/menu.fxml";
	public static final String FXML_VERDATOS = "/fxml/verDatos.fxml";
	public static final String FXML_CAMBIARCONTRASEÑA = "/fxml/cambiarContraseña.fxml";
	public static final String FXML_CONSULTARREGISTRO = "/fxml/consultarRegistro.fxml";
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
            	ConsultarRegistroController loginController = (ConsultarRegistroController) controller;
                loginController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof VerDatosController) {
            	VerDatosController loginController = (VerDatosController) controller;
                loginController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof CambiarContraseñaController) {
            	CambiarContraseñaController loginController = (CambiarContraseñaController) controller;
                loginController.setStage(primaryStage);  // Pasamos el Stage
            }
            else if (controller instanceof RegistroHorasController) {
            	RegistroHorasController loginController = (RegistroHorasController) controller;
                loginController.setStage(primaryStage);  // Pasamos el Stage
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
	
}




