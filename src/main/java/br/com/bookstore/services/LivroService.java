package br.com.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookstore.domain.Categoria;
import br.com.bookstore.domain.Livro;
import br.com.bookstore.repositories.LivroRepository;
import br.com.bookstore.services.exceptions.ObjectNotFoundException;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " TIPO: " + Livro.class.getName()));
	}
	
	public List<Livro> findAll(Integer id_cat){
		categoriaService.findById(id_cat);
		return repository.findAllByCategoria(id_cat);
	}
	
	public Livro create(Integer id_cat, Livro obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(id_cat);
		obj.setCategoria(cat);
		return repository.save(obj);
	}
	
	public Livro update(Integer id, Livro obj) {
		Livro newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(Livro newObj, Livro obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setNome_autor(obj.getNome_autor());
		newObj.setTexto(obj.getTexto());
		newObj.setPreco_capa(obj.getPreco_capa());
	}

	public void delete(Integer id) {
		Livro obj = findById(id);
		repository.delete(obj);
	}

}
