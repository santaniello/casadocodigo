package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

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

	/*
	 * O Spring faz a conversão do texto do form para uma data no formato
	 * dd/MM/yyyy com a anotação @DateTimeFormat(pattern="dd/MM/yyyy")
	 */
	@DateTimeFormat // não precisamos colocar o pattern pois configuramos na
					// classe AppWebConfiguration
	private Calendar dataLancamento;

	private String sumarioPath;

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
		return this.precos;
	}

	public void adicionaPreco(Preco precos) {
		this.precos.add(precos);
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas
				+ ", precos=" + precos + "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public BigDecimal precoPara(TipoPreco tipoPreco) {
		return precos.stream().filter(preco -> preco.getTipo().equals(tipoPreco)).findFirst().get().getValor();
	}

}
