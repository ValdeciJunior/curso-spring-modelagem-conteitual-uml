package br.com.rolimvaldeci.cursomc.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaRescource {
	
	@GetMapping("/teste.json")
	public String listar() {
		return "O rest está funcionando";
	}
	
}
