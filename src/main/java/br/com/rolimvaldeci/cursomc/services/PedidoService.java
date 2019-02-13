package br.com.rolimvaldeci.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rolimvaldeci.cursomc.domain.Pedido;
import br.com.rolimvaldeci.cursomc.repositories.PedidoRepository;
import br.com.rolimvaldeci.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido get(Integer id) {
		/*
		 * O findById retorna na verdade um Optional<Categoria>
		 * */
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
