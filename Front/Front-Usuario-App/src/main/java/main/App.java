package main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.gui.AppController;

public class App extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		AppController controller = new AppController(primaryStage);
		controller.changeScene(AppController.FXML_LOGIN);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Aplicación de Horas");
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch();
	}
}
