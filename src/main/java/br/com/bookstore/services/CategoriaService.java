package br.com.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bookstore.domain.Categoria;
import br.com.bookstore.dtos.CategoriaDTO;
import br.com.bookstore.repositories.CategoriaRepository;
import br.com.bookstore.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Transactional(readOnly = true)
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + " TIPO: " + Categoria.class.getName()));
	}
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	@Transactional
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	@Transactional
	public Categoria update(Integer id, CategoriaDTO objDTO) {
		Categoria obj = repository.getReferenceById(id);
		obj.setNome(objDTO.getNome());
		obj.setDescricao(objDTO.getDescricao());
		return repository.save(obj);

	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new br.com.bookstore.services.exceptions.DataIntegrityViolationException(
					"Categoria não pode ser deletado, Possui livros associados!");
		}
	}

}
