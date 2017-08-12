package br.com.casadocodigo.loja.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.JPATestConfiguration;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.repositories.ProdutoRepository;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPATestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Test
	public void testSaveProduto() {
		Produto produtoSaved = produtoRepository.save(new ProdutoBuilder("Design Patters com Java", 
																		 "Livro sobre Design Patters com Java",
																		  444).build());
		
		assertNotNull("O valor do id não deve ser nulo!",produtoSaved.getId());
	}

}
