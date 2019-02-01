package br.com.rolimvaldeci.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rolimvaldeci.cursomc.domain.Categoria;
import br.com.rolimvaldeci.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Categoria get(Integer id) {
		
	/*
	 * O findById retorna na verdade um Optional<Categoria>
	 * */
		return categoriaRepository.findById(id).orElse(null);
	}
	
}
