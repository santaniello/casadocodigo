package br.com.casadocodigo.loja.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.confs.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.models.Preco;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, ProdutoRepository.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
@Transactional
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Test
	public void testSaveProduto() {
		ProdutoBuilder builder = new ProdutoBuilder().comTitulo("Java 8")
				.comDescricao("Aprendendo Java 8")
				.comPaginas(444)
				.comDataLancamento(Calendar.getInstance())
				.comPreco(new Preco("59.00",TipoPreco.COMBO));
		
		Produto produtoSalvo = produtoRepository.save(builder.constroi());		
		assertNotNull("O produto salvo   não pode ser nulo!",produtoSalvo);
		assertNotNull("A lista de preços não pode ser nula!",produtoSalvo.getPrecos().get(0));			
	}
	
	@Test
	public void testlistaTodosOsProdutos() {
		ProdutoBuilder builder = new ProdutoBuilder().comTitulo("Java 8")
				.comDescricao("Aprendendo Java 8")
				.comPaginas(444)
				.comDataLancamento(Calendar.getInstance())
				.comPreco(new Preco("59.00",TipoPreco.COMBO));
		
		Produto produtoSalvo = produtoRepository.save(builder.constroi());		
		
		assertNotNull("O produto salvo   não pode ser nulo!",produtoSalvo);
		assertNotNull("A lista de preços não pode ser nula!",produtoSalvo.getPrecos().get(0));
		
		List<Produto> produtos = produtoRepository.findAll();		
		assertTrue("A lista de produtos deve ser maior que 0!", produtos.size() > 0);		
	}
	
	@Test
	public void testBuscaProdutoPorId(){
		 ProdutoBuilder builder = new ProdutoBuilder().comTitulo("Java 8")
		.comDescricao("Aprendendo Java 8")
		.comPaginas(444)
		.comDataLancamento(Calendar.getInstance())
		.comPreco(new Preco("59.00",TipoPreco.COMBO));
				
		Produto produtoSalvo = produtoRepository.save(builder.constroi());		
		
		assertNotNull("O produto salvo   não pode ser nulo!",produtoSalvo);
		assertNotNull("A lista de preços não pode ser nula!",produtoSalvo.getPrecos().get(0));
		 
		Produto produtoEncontrado = produtoRepository.findOne(produtoSalvo.getId());
		assertNotNull("O produto buscado  não pode ser nulo!",produtoEncontrado);
	}	
}
