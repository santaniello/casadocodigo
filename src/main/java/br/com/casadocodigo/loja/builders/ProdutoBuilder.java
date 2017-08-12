package br.com.casadocodigo.loja.builders;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoBuilder {

	private Produto produto = new Produto();

	public Produto build() {
		return this.produto;
	}
	
	public ProdutoBuilder() {}
	
	public ProdutoBuilder(String titulo, String descricao, int paginas) {
		this.produto.setTitulo(titulo);
		this.produto.setDescricao(descricao);
		this.produto.setPaginas(paginas);
	}	

	public ProdutoBuilder withId(Long id) {
		this.produto.setId(id);
		return this;
	}

	public ProdutoBuilder withTitulo(String titulo) {
		this.produto.setTitulo(titulo);
		return this;
	}

	public ProdutoBuilder withDescricao(String descricao) {
		this.produto.setDescricao(descricao);
		return this;
	}

	public ProdutoBuilder withPaginas(int paginas) {
		this.produto.setPaginas(paginas);
		return this;
	}
}
