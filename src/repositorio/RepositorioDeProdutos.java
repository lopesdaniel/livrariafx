package repositorio;

import br.com.casadocodigo.livraria.Autor;
import br.com.casadocodigo.livraria.produtos.Livro;
import br.com.casadocodigo.livraria.produtos.LivroFisico;
import br.com.casadocodigo.livraria.produtos.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RepositorioDeProdutos {
	
	public ObservableList<Produto> lista(){
		
		Autor autor = new Autor();
		autor.setNome("Rodrigo Turini");
		autor.setEmail("rodrigo.turini@caelum.com.br");
		autor.setCpf("123.456.789-10");
		
		Livro livro = new LivroFisico(autor);
		livro.setNome("Java 8 prático");
		livro.setDescricao("Novos recursos da linguagem");
		livro.setValor(59.90);
		livro.setIsbn("987-85-66250-46-6");
		
		Livro maisUmLivro = new LivroFisico(autor);
		maisUmLivro.setNome("Desbravando a O.O");
		maisUmLivro.setDescricao("Livro de Java e O.O");
		maisUmLivro.setValor(59.90);
		maisUmLivro.setIsbn("321-54-67890-11-2");
		
		Autor outroAutor = new Autor();
		outroAutor.setNome("Paulo Silveira");
		outroAutor.setEmail("paulo.silveira@caelum.com.br");
		outroAutor.setCpf("123.456.789-11");
		
		Livro outroLivro = new LivroFisico(outroAutor);
		outroLivro.setNome("Lógica de Programação");
		outroLivro.setDescricao("Crie seus primeiros programas");
		outroLivro.setValor(59.90);
		outroLivro.setIsbn("987-85-66250-22-0");
		
		
		return FXCollections.observableArrayList(livro, maisUmLivro, outroLivro);
		
	}

}
