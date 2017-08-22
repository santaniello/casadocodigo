package br.com.casadocodigo.loja.repositories;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

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
				 										    new Preco("59.00",TipoPreco.COMBO), Calendar.getInstance());		
		Produto produtoSaved = produtoRepository.save(produtoBuilder.constroi());		
		assertNotNull("O produto salvo   não pode ser nulo!",produtoSaved);
		assertNotNull("A lista de preços não pode ser nula!",produtoSaved.getPrecos().get(0));			
	}
	
	@Test
	public void testlistaTodosOsProdutos() {
		ProdutoBuilder produtoBuilder = new ProdutoBuilder("Design Patters com Java", 
				 										   "Livro sobre Design Patters com Java",
				 										    444,
				 										    new Preco("59.00",TipoPreco.COMBO), Calendar.getInstance());		
		Produto produtoSaved = produtoRepository.save(produtoBuilder.constroi());		
		
		assertNotNull("O produto salvo   não pode ser nulo!",produtoSaved);
		assertNotNull("A lista de preços não pode ser nula!",produtoSaved.getPrecos().get(0));
		
		List<Produto> produtos = produtoRepository.findAll();		
		assertTrue("A lista de produtos deve ser maior que 0!", produtos.size() > 0);		
	}	
}
