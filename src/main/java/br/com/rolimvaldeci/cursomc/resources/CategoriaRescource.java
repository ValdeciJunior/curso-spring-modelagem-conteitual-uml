package br.com.rolimvaldeci.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rolimvaldeci.cursomc.domain.Categoria;
import br.com.rolimvaldeci.cursomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaRescource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Integer id) {
		return ResponseEntity.ok().body(service.get(id));
	}
	
//	@PostMapping
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> cadastrar(@RequestBody Categoria obj){
		obj = service.cadastrar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
