package br.com.rolimvaldeci.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rolimvaldeci.cursomc.domain.Pagamento;;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
	
}
