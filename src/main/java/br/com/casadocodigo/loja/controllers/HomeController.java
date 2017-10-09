package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.services.ProdutoService;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping("/")
	// Estamos informando que este método é cacheavel e o nome do cache é produtoHome
	@Cacheable(value="produtoHome") 
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("produtos",produtoService.listar());
		return mv;
	}

}
