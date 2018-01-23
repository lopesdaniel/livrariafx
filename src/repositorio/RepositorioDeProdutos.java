package repositorio;

import static javafx.collections.FXCollections.observableArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.casadocodigo.livraria.Autor;
import br.com.casadocodigo.livraria.produtos.LivroFisico;
import br.com.casadocodigo.livraria.produtos.Produto;
import db.ConnectionFactory;
import javafx.collections.ObservableList;

public class RepositorioDeProdutos {
	
	public ObservableList<Produto> lista(){
		
		ObservableList<Produto> produtos = observableArrayList();
		PreparedStatement ps;
		try(Connection conn = new ConnectionFactory().getConnection()) {
			ps = conn.prepareStatement("select * from produtos");
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				LivroFisico livro = new LivroFisico(new Autor());
				livro.setNome(resultSet.getString("nome"));
				livro.setDescricao(resultSet.getString("descricao"));
				livro.setValor(resultSet.getDouble("valor"));
				livro.setIsbn(resultSet.getString("isbn"));
				produtos.add(livro);
			}
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return produtos;
		
	}

}
