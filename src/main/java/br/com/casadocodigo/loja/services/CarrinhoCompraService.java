package br.com.casadocodigo.loja.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Service
public class CarrinhoCompraService {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	public void gravar(Produto produto){
		produtoDao.gravar(produto);
	}
	
	public List<Produto> listar(){
		return produtoDao.listar();
	}

	public Produto find(long id) {
		return produtoDao.find(id);		
	}	
}
