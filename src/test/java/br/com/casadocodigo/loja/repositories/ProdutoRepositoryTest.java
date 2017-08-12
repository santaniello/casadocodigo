package br.com.casadocodigo.loja.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.JPATestConfiguration;
import br.com.casadocodigo.loja.models.Preco;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPATestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Test
	public void testSaveProduto() {
		ProdutoBuilder produtoBuilder = new ProdutoBuilder("Design Patters com Java", 
				 										   "Livro sobre Design Patters com Java",
				 										    444,
				 										    new Preco("59.00",TipoPreco.COMBO));		
		Produto produtoSaved = produtoRepository.save(produtoBuilder.build());		
		assertNotNull("O produto salvo   não pode ser nulo!",produtoSaved);
		assertNotNull("A lista de preços não pode ser nula!",produtoSaved.getPrecos().get(0));			
	}
}
