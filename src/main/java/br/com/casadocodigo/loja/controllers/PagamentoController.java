package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Usuario;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;
	
	
	/**
	 * Enviador de emails do Spring
	 * 
	 * */
	@Autowired
	private MailSender sender;
	
	
	/**
	 * Objeto nativo do Spring para trabalhar com requisições Rest...
	 * */
	@Autowired
	private RestTemplate restTemplate;
	
	/* O callable é um recurso do servlet 3.0 que permite que este método seja chamado de forma 
	 * assincrona permitindo que outros usuário simultaneos do sistema usem o mesmo sem a necessidade de 
	 * esperar o processamento desta thread 
	 * 
	 * @AuthenticationPrincipal Usuario é o usuário da sessão que o Spring security esta controlando...
	 * 
	 * 
	 * */	
	@RequestMapping(value="/finalizar",method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario,RedirectAttributes model){
		/*
		 *  Esta forma de usar lambda nos permite criar um objeto do mesmo tipo esperado pelo retorno 
		 *  do método, evitando que criemos uma classe anônima. Neste caso é perfeitamente aplicável
		 *  o recurso, por que na interface Callable só há um método, de nome call.
		 * 
		 * 
		 * */
		return () -> {
	        try {
	            String uri = "http://book-payment.herokuapp.com/payment";
	            enviaEmailCompraProduto(usuario);
	            String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
	            model.addFlashAttribute("sucesso", response);
	            return new ModelAndView("redirect:/produtos");
	        } catch (HttpClientErrorException e) {
	            e.printStackTrace();
	            model.addFlashAttribute("falha", "Valor maior que o permitido");
	            return new ModelAndView("redirect:/produtos");
	        }
	    };
	}	
	
	private void enviaEmailCompraProduto(Usuario usuario) {
	    SimpleMailMessage email = new SimpleMailMessage();
	    email.setSubject("Compra finalizada com sucesso");
	    email.setTo("felipe_sbc_sp@hotmail.com");
	    email.setText("Compra aprovada com sucesso no valor de " + carrinho.getTotal());
	    email.setFrom("alura.springmvc@gmail.com");
	    sender.send(email);
	}
	
}
