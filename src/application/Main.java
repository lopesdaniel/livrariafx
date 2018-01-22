package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Group group = new Group();
		Scene scene = new Scene(group, 690, 510);
		
		Label label = new Label("Listagem de Livros");
		label.setFont(Font.font("Arial Black", FontPosture.REGULAR, 40));
		label.setPadding(new Insets(20, 0, 10, 30));
		
		group.getChildren().addAll(label);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistemas da livraria com Java FX");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
