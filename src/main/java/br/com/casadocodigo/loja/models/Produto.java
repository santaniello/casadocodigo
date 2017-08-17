package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@Lob
	private String descricao;
	private int paginas;
	/*
	 * Podemos fazer uma relação de produtos com seus preços em duas tabelas
	 * diferentes no banco de dados, usando o id do produto para estabelecer
	 * essa relação OneToMany, ou seja, um produto para vários preços. Mas neste
	 * contexto, isso não faria muito sentido, porque teríamos um id para o
	 * preço e não precisamos disso, pois não vamos reutilizar o preço do
	 * produto.
	 * 
	 * @ElementCollection indica que este atributo é uma coleção de elementos.
	 */
	@ElementCollection
	private List<Preco> precos = new ArrayList<Preco>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public List<Preco> getPrecos() {
		return  this.precos;
	}

	public void adicionaPreco(Preco precos) {
		this.precos.add(precos);
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas
				+ ", precos=" + precos + "]";
	}
}
