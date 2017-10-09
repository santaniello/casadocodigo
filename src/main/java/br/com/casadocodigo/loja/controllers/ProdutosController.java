package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.infra.FileUpload;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.services.ProdutoService;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private FileUpload fileUpload;

	/*
	 * No InitBinder, faremos a validação do nosso produto que está vindo do
	 * formulário...
	 * 
	 * Observação: O Binder, por assim dizer, é o responsável por conectar duas
	 * coisas. Por exemplo, os dados do formulário com o objeto da classe
	 * Produto, como já fizemos anteriormente.
	 *
	 */
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}

	/*
	 * O objeto RedirectAttributes permite enviar informações entre requisições.
	 * Observação: Atributos do tipo Flash têm uma particularidade que é
	 * interessante observar. Eles só duram até a próxima requisição, ou seja,
	 * transportam informações de uma requisição para a outra e então, deixam de
	 * existir.
	 * 
	 * Vamos enviar a mensagem de sucesso do método gravar para o método listar
	 * usando o atributo Flash...
	 * 
	 * Note que o BindingResult vem logo após o atributo que está assinado com a
	 * anotação @Valid, essa ordem não é por acaso, precisa ser desta forma para
	 * que o Spring consiga fazer as validações da forma correta.
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)
	// A anotação @CacheEvict limpa o cache produtoHome quando este método é chamado... allEntries significa que ele vai limpar o cache inteiro...
	@CacheEvict(value="produtoHome",allEntries=true)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return form(produto);
		}

		String path = fileUpload.upload("arquivos-sumario", sumario);
		produto.setSumarioPath(path);

		produtoService.gravar(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:/produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoService.listar();
		ModelAndView modelAndView = new ModelAndView("/produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Long id){
	    ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
	    Produto produto = produtoService.find(id);
	    modelAndView.addObject("produto", produto);
	    return modelAndView;
	}	

}
