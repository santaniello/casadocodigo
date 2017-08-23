package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Classe que será usada como classe de configuração do servlet do SpringMVC.
 */

@EnableWebMvc // Habilita o SpringMVC
@ComponentScan(basePackages = { "br.com.casadocodigo.loja.controllers", 
								"br.com.casadocodigo.loja.daos",
								"br.com.casadocodigo.loja.services",
								"br.com.casadocodigo.loja.infra"}) // Classes que serão mapeadas pelo Spring...
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * Método que carregará nossos arquivos de mensagens de validação...
	 * 
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages"); // informando aonde está
														// nosso arquivo
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1); // Tempo que o Spring fará cache do
											// arquivo...
		return messageSource;
	}

	/**
	 * Esse método seta o padrão de data deafult para nossa aplicação eliminando
	 * a necessidade de colocar o pattern dd/MM/yyyy na
	 * anotação @DateTimeFormat(pattern="dd/MM/yyyy") ficando apenas
	 * 
	 * @DateTimeFormat
	 */
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
		formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatterRegistrar.registerFormatters(conversionService);
		return conversionService;
	}

	/**
	 *  Upload de arquivos com Spring.
	 * 
	 *  MultipartResolver se refere a um resolvedor de dados multimidia. 
	 *  Quando temos texto e arquivos por exemplo. Os arquivos podem ser: 
	 *  imagem, PDF e outros. Este objeto é que identifica cada um dos recursos 
	 *  enviados e nos fornece uma forma mais simples de manipulalos.
	 * 
	 **/	
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	/**
	 * Configuração dos arquivos estaticos no Spring...
	 * 
	 * Substitui a linha:
	 * 
	 * <!-- Handles HTTP GET requests for /resources/** by efficiently serving 
	 *	up static resources in the ${webappRoot}/resources directory -->
	 *	
	 *	<resources mapping="/resources/**" location="/resources/" />
	 * 
	 * */	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }
}
