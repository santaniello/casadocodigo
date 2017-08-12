package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Classe que será usada como classe de configuração do servlet do SpringMVC.
 * */

@EnableWebMvc //Habilita o SpringMVC
@ComponentScan(basePackages={"br.com.casadocodigo.loja.controllers",
							 "br.com.casadocodigo.loja.daos",
							 "br.com.casadocodigo.loja.services"}) // Classes que serão mapeadas pelo Spring...
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/views/");
	    resolver.setSuffix(".jsp");
	    return resolver;
	}

}
