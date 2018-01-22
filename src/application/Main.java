package application;
	
import br.com.casadocodigo.livraria.produtos.Produto;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import repositorio.RepositorioDeProdutos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Group group = new Group();
		Scene scene = new Scene(group, 690, 510);
		
		ObservableList<Produto> produtos = new RepositorioDeProdutos().lista();
		
		TableView tableView = new TableView<>(produtos);
		
		TableColumn nomeColumn = new TableColumn("Nome");
		nomeColumn.setMinWidth(180);
		nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));
		
		TableColumn descColumn = new TableColumn("Descri��o");
		descColumn.setMinWidth(230);
		descColumn.setCellValueFactory(new PropertyValueFactory("descricao"));
		
		TableColumn valorColumn = new TableColumn("Valor");
		valorColumn.setMinWidth(60);
		valorColumn.setCellValueFactory(new PropertyValueFactory("valor"));
		
		TableColumn isbnColumn = new TableColumn("ISBN");
		isbnColumn.setMinWidth(180);
		isbnColumn.setCellValueFactory(new PropertyValueFactory("isbn"));
		
		tableView.getColumns().addAll(nomeColumn, descColumn, valorColumn, isbnColumn);
		
		Label label = new Label("Listagem de Livros");
		label.setFont(Font.font("Arial Black", FontPosture.REGULAR, 40));
		label.setPadding(new Insets(20, 0, 10, 30));
		
		group.getChildren().addAll(label, tableView);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistemas da livraria com Java FX");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}