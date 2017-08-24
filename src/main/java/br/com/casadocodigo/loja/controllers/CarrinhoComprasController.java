package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.services.CarrinhoCompraService;

@Controller
@RequestMapping("/carrinho")
/**
 * A anotação @Scope indica que o carrinho de compras controller ira durar durante a 
 * requisição do usuário (irá ser instanciado um por requisição)...
 * 
 * OBS: A duração do Scopo é indicada através da interface WebApplicationContext.
 * 
 * */
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {
	
	@Autowired
    private CarrinhoCompras carrinho;
	
	@Autowired
	private CarrinhoCompraService carrinhoService;
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ModelAndView add(Integer produtoId, TipoPreco tipo){
	    ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
	    CarrinhoItem carrinhoItem = criaItem(produtoId, tipo);
	    carrinho.add(carrinhoItem);
	    return modelAndView;
	}	
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipo){
	    Produto produto = carrinhoService.find(produtoId);
	    CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipo);
	    return carrinhoItem;
	}	
}