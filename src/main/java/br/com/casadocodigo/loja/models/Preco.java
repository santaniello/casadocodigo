package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

/* @Embeddable Permite ao Spring relacionar e portar os elementos 
 * de preço para dentro da coleção List<Preco> precos da classe Produto. * 
 */
@Embeddable
public class Preco {
	private BigDecimal valor;
	private TipoPreco tipo;
	
	public Preco() {}
	
	public Preco(String valor, TipoPreco tipo){
		this.valor = new BigDecimal(valor);
		this.tipo  = tipo;
	}
	

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}

}
