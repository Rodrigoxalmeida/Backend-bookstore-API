package br.com.bookstore.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookstore.domain.Categoria;
import br.com.bookstore.domain.Livro;
import br.com.bookstore.repositories.CategoriaRepository;
import br.com.bookstore.repositories.LivroRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;

	public void instanciaBancoDeDados() {
		
		Categoria cat1 = new Categoria(null,"Informatica", "Livro de informatica");
		Categoria cat2 = new Categoria(null,"Ficção Científica", "Livros de Ficção");
		Categoria cat3 = new Categoria(null,"Biografia", "Livros de Biografia");
				;
		Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
		Livro l2 = new Livro(null, "Engenharia de Software", "Louis V. Gerstner", "Lorem ipsum", cat1);
		Livro l3 = new Livro(null, "The time machine", "H.G. Wells", "Lorem ipsum", cat2);
		Livro l4 = new Livro(null, "The War of the Worlds", "H.G. Wells", "Lorem ipsum", cat2);
		Livro l5 = new Livro(null, "I, Robot", "Isaac Asimov", "Lorem ipsum", cat2);
		Livro l6 = new Livro(null, "O Fascinante império de Stev Jobs", "Stev Jobs", "Lorem ipsum" ,cat3);
		
		cat1.getLivros().addAll(Arrays.asList(l1,l2));
		cat2.getLivros().addAll(Arrays.asList(l3,l4,l5));
		cat3.getLivros().addAll(Arrays.asList(l6));
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		this.livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5,l6));
		
	}
	
}
