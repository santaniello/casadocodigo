package br.com.casadocodigo.loja.builders;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;

public class CarrinhoComprasBuilder {

	private CarrinhoCompras carrinho = null;
			
	public CarrinhoComprasBuilder(CarrinhoItem item){
		carrinho = new CarrinhoCompras();
		carrinho.add(item);
	}	
	
	public CarrinhoComprasBuilder comItem(CarrinhoItem item) {
		this.carrinho.add(item);
		return this;
	}
	
	public CarrinhoCompras constroi(){
		return this.carrinho;
	}	
}
