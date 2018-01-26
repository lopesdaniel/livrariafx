package application;
	
import java.io.IOException;

import br.com.casadocodigo.livraria.produtos.Produto;
import dao.ProdutoDAO;
import io.Exportador;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Group group = new Group();
		Scene scene = new Scene(group, 690, 510);
		
		ObservableList<Produto> produtos = new ProdutoDAO().lista();
		
		TableView tableView = new TableView<>(produtos);
		
		TableColumn nomeColumn = new TableColumn("Nome");
		nomeColumn.setMinWidth(180);
		nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));
		
		TableColumn descColumn = new TableColumn("Descrição");
		descColumn.setMinWidth(230);
		descColumn.setCellValueFactory(new PropertyValueFactory("descricao"));
		
		TableColumn valorColumn = new TableColumn("Valor");
		valorColumn.setMinWidth(60);
		valorColumn.setCellValueFactory(new PropertyValueFactory("valor"));
		
		TableColumn isbnColumn = new TableColumn("ISBN");
		isbnColumn.setMinWidth(180);
		isbnColumn.setCellValueFactory(new PropertyValueFactory("isbn"));
		
		tableView.getColumns().addAll(nomeColumn, descColumn, valorColumn, isbnColumn);
		
		final VBox vbox = new VBox(tableView);
		vbox.setPadding(new Insets(70,0,0,10));
		
		Label label = new Label("Listagem de Livros");
		label.setFont(Font.font("Arial Black", FontPosture.REGULAR, 40));
		label.setPadding(new Insets(10, 0, 10, 30));
		
		Label progresso = new Label();
		progresso.setLayoutX(485);
		progresso.setLayoutY(30);
		
		Button button = new Button("Exportar CSV");
		button.setLayoutX(575);
		button.setLayoutY(25);
		
		button.setOnAction(event -> {
			Task<Void> task = new Task<Void>(){
				@Override
				protected Void call() throws Exception{
					dormePorVinteSegundos();
					exportaEmCSV(produtos);
					
					return null;
				}
			};
			task.setOnRunning(e -> progresso.setText("Exportando..."));
			task.setOnSucceeded(e -> progresso.setText("Concluído !"));
			
			new Thread(task).start();
		});
		
		group.getChildren().addAll(label, progresso, vbox, button);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistemas da livraria com Java FX");
		primaryStage.show();
	}
	
	private void exportaEmCSV(ObservableList<Produto> produtos){
		try{
			new Exportador().paraCSV(produtos);
		}catch(IOException e){
			System.out.println("Erro ao exportar: "+ e);
		}
	}
	
	private void dormePorVinteSegundos(){
		try{
			Thread.sleep(20000);
		}catch(InterruptedException e){
			System.out.println("Ops, ocorreu um erro: " + e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
