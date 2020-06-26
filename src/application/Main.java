package application;
	
import gui.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/resources/view/main_container.fxml"));
			BorderPane root = loader.load();
			Scene scene = new Scene(root,1366,768);
			Controller c = loader.getController();
			
			c.setStage(primaryStage);
			scene.getStylesheets().add(getClass().getResource("/gui/resources/css/application.css").toExternalForm());
			primaryStage.setTitle("Système expert médical");
			primaryStage.setScene(scene);

			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
