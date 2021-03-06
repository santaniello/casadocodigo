package br.com.casadocodigo.loja.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

/**
 * Classe que será usada como classe de configuração do servlet do SpringMVC.
 */

@EnableWebMvc // Habilita o SpringMVC
@EnableCaching // Habilita o cache na aplicação
@ComponentScan(basePackages = { "br.com.casadocodigo.loja.controllers", 
								"br.com.casadocodigo.loja.daos",
								"br.com.casadocodigo.loja.services",
								"br.com.casadocodigo.loja.infra",
								"br.com.casadocodigo.loja.models"
								}) // Classes que serão mapeadas pelo Spring...
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		// Estamos expondo a nossa bean para ser acessada nas views da nossa aplicação...
		resolver.setExposedContextBeanNames("carrinhoCompras");
		return resolver;
	}

	/**
	 * Método que carregará nossos arquivos de mensagens de validação...
	 * 
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages"); // informando aonde está nosso arquivo
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1); // Tempo que o Spring fará cache do arquivo...
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
	
	
	/**
	 * Por padrão o spring pega qualquer requisição, inclusive o link de arquivos css e javascript. 
	 * Para corrigir isso precisamos fazer a nossa classe de configuração herdar de WebMvcConfigurerAdapter e 
	 * sobreescrever o método configureDefaultServletHandling. 
	 * 
	 * 
	 * 
	 * */
	
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	

	/**
	 * Objeto nativo do Spring para trabalhar com requisições Rest...
	 * */
	@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
	
	
	/**
	 * Criando um gerenciador de caches na aplicação...
	 * */
	@Bean
    public CacheManager cacheManager() {
        // return new ConcurrentMapCacheManager(); // Cache usado apenas para testes
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
        GuavaCacheManager manager = new GuavaCacheManager();
        manager.setCacheBuilder(builder);
        return manager;		
    }
	
	/**
	 * A Técnica chamada de Content Negociation é possível que uma mesma URL retorne as informações em formatos 
	 * diferentes. Exemplo: acessar a URL localhost:8080/casadocodigo/produtos/5 traria como resposta o HTML 
	 * da página de detalhes daquele produto, enquanto acessar localhost:8080/casadocodigo/produtos/5.json 
	 * retornaria o JSON que representa aquele produto.
	 * 
	 * */
	
	
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager){
	    List<ViewResolver> viewResolvers = new ArrayList<>();
	    viewResolvers.add(internalResourceViewResolver());
	    // criando um resolver para json ...
	    viewResolvers.add(new JsonViewResolver());
	  //  viewResolvers.add(new XmlViewResolver());
	    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
	    resolver.setViewResolvers(viewResolvers);
	    resolver.setContentNegotiationManager(manager);
	    return resolver;
	}
	
	// Trabalhando com internacionalização...
	
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	@Bean
	public LocaleResolver localeResolver(){
	    return new CookieLocaleResolver();
	}
	
	
	/**
	 * Implementando um enviador de e-mails...
	 * 
	 * */
	@Bean
	public MailSender mailSender(){
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setUsername("santaniello.fatec@gmail.com");
	    mailSender.setPassword("111111");
	    mailSender.setPort(587);
	    
	    Properties mailProperties = new Properties();
	    mailProperties.put("mail.smtp.auth", true);
	    mailProperties.put("mail.smpt.starttls.enable", true);
	    mailProperties.put("mail.smtp.starttls.enable", true);  
	    mailProperties.put("mail.smtp.socketFactory.port", 465);  
	    mailProperties.put("mail.smtp.socketFactory.fallback", false);  
	    mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  

	    mailSender.setJavaMailProperties(mailProperties);
	    return mailSender;
	}
	
	
	
}
