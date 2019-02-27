package br.com.rolimvaldeci.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rolimvaldeci.cursomc.domain.Cliente;
import br.com.rolimvaldeci.cursomc.dto.ClienteDTO;
import br.com.rolimvaldeci.cursomc.dto.ClienteNewDTO;
import br.com.rolimvaldeci.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteRescource {
	
	@Autowired
	private ClienteService service;
	
	@PostMapping
	public ResponseEntity<Void> cadastrar(@Valid @RequestBody ClienteNewDTO objDTO){
		Cliente obj = service.fromDTO(objDTO);
		obj = service.cadastrar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> get(@PathVariable("id") Integer id) {
		return ResponseEntity.ok().body(service.get(id));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable("id") Integer id){
		Cliente obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> categorias() {
		List<Cliente> list = service.categorias();
		List<ClienteDTO> categoriaDTOs = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriaDTOs);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> page(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linePerPage", defaultValue="24") Integer linePerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cliente> list = service.page(page, linePerPage, orderBy, direction);
		Page<ClienteDTO> categoriaDTOs = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(categoriaDTOs);
	}
	
}
