package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.services.ProdutoService;

@Controller
@RequestMapping("produtos")
public class ProdutosController {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping("/form")
	public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("tipos", TipoPreco.values());
        return modelAndView;
    }

	/* O objeto RedirectAttributes permite enviar informações entre requisições.
	 * Observação: Atributos do tipo Flash têm uma particularidade que é 
	 * interessante observar. Eles só duram até a próxima requisição, 
	 * ou seja, transportam informações de uma requisição para a outra
	 * e então, deixam de existir.
	 * 
	 * Vamos enviar a mensagem de sucesso do método gravar para o método listar
	 * usando o atributo Flash...
	 * 
	 * */
    @RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(Produto produto, RedirectAttributes redirectAttributes) {
		System.out.println(produto);
		produtoService.gravar(produto);
		redirectAttributes.addFlashAttribute("sucesso","Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:produtos");
	}
	
    @RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar(){
	    List<Produto> produtos = produtoService.listar();
	    ModelAndView modelAndView = new ModelAndView("/produtos/lista");
	    modelAndView.addObject("produtos", produtos);
	    return modelAndView;
	}

}
