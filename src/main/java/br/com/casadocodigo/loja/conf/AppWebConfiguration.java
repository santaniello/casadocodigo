package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
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
	
	/**
	 * Método que carregará nossos arquivos de mensagens de validação...
	 * 
	 * */	
	@Bean
	public MessageSource messageSource(){
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("/WEB-INF/messages"); // informando aonde está nosso arquivo
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setCacheSeconds(1); // Tempo que o Spring fará cache do arquivo...
	    return messageSource;
	}
	
	/**
	 * Esse método seta o padrão de data deafult para nossa aplicação
	 * eliminando a necessidade de colocar o pattern dd/MM/yyyy na 
	 * anotação @DateTimeFormat(pattern="dd/MM/yyyy") ficando apenas 
	 * @DateTimeFormat
	 * */	
	@Bean
	public FormattingConversionService mvcConversionService(){
	    DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
	    DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
	    formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
	    formatterRegistrar.registerFormatters(conversionService);
	    return conversionService;
	}
}
