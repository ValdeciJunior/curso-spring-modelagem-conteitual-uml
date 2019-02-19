package br.com.rolimvaldeci.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.rolimvaldeci.cursomc.domain.Categoria;
import br.com.rolimvaldeci.cursomc.repositories.CategoriaRepository;
import br.com.rolimvaldeci.cursomc.services.exceptions.DateIntegrityException;
import br.com.rolimvaldeci.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria get(Integer id) {
		/*
		 * O findById retorna na verdade um Optional<Categoria>
		 * */
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria cadastrar(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		get(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		get(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DateIntegrityException("Não é possível excluir uma categoria que tem produtos");
		}
		
	}
	
}
