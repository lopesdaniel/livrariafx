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

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Group group = new Group();
		Scene scene = new Scene(group, 690, 510);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		ObservableList<Produto> produtos = new ProdutoDAO().lista();
		
		TableView tableView = new TableView<>(produtos);
		
		TableColumn nomeColumn = criaColuna("Nome", 180, "nome");
		TableColumn descColumn = criaColuna("Descrição", 230, "descricao");
		TableColumn valorColumn = criaColuna("Valor", 60, "valor");
		TableColumn isbnColumn = criaColuna("ISBN", 180, "isbn");
		
		tableView.getColumns().addAll(nomeColumn, descColumn, valorColumn, isbnColumn);
		
		final VBox vbox = new VBox(tableView);
		vbox.setId("vbox");
		
		Label label = new Label("Listagem de Livros");
		label.setId("label-listagem");
		
		Label progresso = new Label();
		progresso.setId("label-progresso");
		
		double valorTotal = produtos.stream().mapToDouble(Produto::getValor).sum();
		
		Label labelFooter = new Label(
				String.format("Você tem R$%.2f em estoque, "+"com um total de %d produtos",
						valorTotal, 10));
		labelFooter.setId("label-footer");
		
		Button button = new Button("Exportar CSV");
		
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
		
		group.getChildren().addAll(label, progresso, labelFooter, vbox, button);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistemas da livraria com Java FX");
		primaryStage.show();
	}

	private TableColumn<Produto, String> criaColuna(String titulo, int largura, String atributo) {
		
		TableColumn<Produto, String> column = 
				new TableColumn<Produto, String>(titulo);
		column.setMinWidth(largura);
		
		column.setCellValueFactory(
				new PropertyValueFactory<Produto, String>(atributo));
		
		return column;
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
