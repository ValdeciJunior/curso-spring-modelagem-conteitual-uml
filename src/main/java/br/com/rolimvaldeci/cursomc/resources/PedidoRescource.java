package br.com.rolimvaldeci.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rolimvaldeci.cursomc.domain.Pedido;
import br.com.rolimvaldeci.cursomc.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoRescource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Integer id) {
		return ResponseEntity.ok().body(pedidoService.get(id));
		
	}
	
}
