package br.com.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookstore.domain.Livro;
import br.com.bookstore.repositories.LivroRepository;
import br.com.bookstore.services.exceptions.ObjectNotFoundException;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " TIPO: " + Livro.class.getName()));
	}
	
	public List<Livro> findAll(){
		return repository.findAll();
	}

}
