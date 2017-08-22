package br.com.casadocodigo.loja.builders;

import java.util.Calendar;

import br.com.casadocodigo.loja.models.Preco;
import br.com.casadocodigo.loja.models.Produto;

public class ProdutoBuilder {

	private Produto produto = new Produto();

	public Produto constroi() {
		return this.produto;
	}

	public ProdutoBuilder() {}

	public ProdutoBuilder(String titulo, String descricao, int paginas,Preco preco) {
		this.produto.setTitulo(titulo);
		this.produto.setDescricao(descricao);
		this.produto.setPaginas(paginas);
		this.produto.adicionaPreco(preco);
	}
	
	public ProdutoBuilder(String titulo, String descricao, int paginas,Preco preco, Calendar dataLancamento) {
		this(titulo,descricao,paginas,preco);
		this.produto.setDataLancamento(dataLancamento);		
	}
	

	public ProdutoBuilder comId(Long id) {
		this.produto.setId(id);
		return this;
	}

	public ProdutoBuilder comTitulo(String titulo) {
		this.produto.setTitulo(titulo);
		return this;
	}

	public ProdutoBuilder comDescricao(String descricao) {
		this.produto.setDescricao(descricao);
		return this;
	}

	public ProdutoBuilder comPaginas(int paginas) {
		this.produto.setPaginas(paginas);
		return this;
	}
	
	public ProdutoBuilder comPreco(Preco preco) {
		this.produto.adicionaPreco(preco);
		return this;
	}
	
	public ProdutoBuilder comDataLancamento(Calendar dataLancamento) {
		this.produto.setDataLancamento(dataLancamento);
		return this;
	}
}
