package br.com.casadocodigo.loja.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
