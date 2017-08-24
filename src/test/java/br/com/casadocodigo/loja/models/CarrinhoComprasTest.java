package br.com.casadocodigo.loja.models;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.CarrinhoComprasBuilder;
import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.JPATestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPATestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class CarrinhoComprasTest {
	
	@Test
	public void deveAdicionarItemAoCarrinho() {		
		Produto  produto = new ProdutoBuilder().comTitulo("Java 8")
				.comDescricao("Aprendendo Java 8")
				.comPaginas(444)
				.comDataLancamento(Calendar.getInstance())
				.comPreco(new Preco("59.00",TipoPreco.COMBO)).constroi();
		
		CarrinhoCompras carrinho = new CarrinhoComprasBuilder(new CarrinhoItem(produto, TipoPreco.COMBO)).constroi();		
		assertTrue("A quantidade de itens do carrinho deve ser maior que 0", carrinho.getQuantidade() > 0);				
	}		
}
