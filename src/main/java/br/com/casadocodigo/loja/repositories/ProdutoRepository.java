package br.com.casadocodigo.loja.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
	@Query(value="select * from Produto",nativeQuery=true)
	public List<Produto> findAll();
}
