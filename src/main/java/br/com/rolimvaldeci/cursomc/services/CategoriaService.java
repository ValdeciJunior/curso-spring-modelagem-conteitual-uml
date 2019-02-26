package br.com.rolimvaldeci.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.rolimvaldeci.cursomc.domain.Categoria;
import br.com.rolimvaldeci.cursomc.dto.CategoriaDTO;
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
		Categoria newObj = get(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		get(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DateIntegrityException("Não é possível excluir uma categoria que tem produtos");
		}
		
	}
	
	public List<Categoria> categorias(){
		return repo.findAll();
	}
	
	public Page<Categoria> page(Integer page, Integer linePerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
}
