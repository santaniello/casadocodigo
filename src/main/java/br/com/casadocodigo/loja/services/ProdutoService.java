package br.com.casadocodigo.loja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	public void gravar(Produto produto){
		produtoDao.gravar(produto);
	}
}
