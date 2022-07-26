package br.com.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.bookstore.domain.Livro;
import br.com.bookstore.dtos.LivroDTO;
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
	
	public Livro create(Livro obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Livro update(Integer id, LivroDTO objDTO) {
		Livro obj = findById(id);
		obj.setTitulo(objDTO.getTitulo());
		obj.setNome_autor(objDTO.getNome_autor());	
		obj.setTexto(objDTO.getTexto());			
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new br.com.bookstore.services.exceptions.DataIntegrityViolationException(
					"Erro ao deletar livro!");
		}
	}

}
