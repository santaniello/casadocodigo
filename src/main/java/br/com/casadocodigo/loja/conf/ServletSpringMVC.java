package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	/**
	 * O getRootConfigClasses pede um array de classes de configurações que serão carregadas logo 
	 * ao iniciar a aplicação. 
	 * 
	 * */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppWebConfiguration.class, JPAConfiguration.class, SecurityConfiguration.class };
	}

	/**
	 * O getServletConfigClasses pede um array de classes de configurações que serão carregadas ao 
	 * fazermos a nossa primeira requisição a aplicação.
	 **/
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	/**
	 * O getServletMappings pede um array com os mapeamentos que queremos que
	 * nosso servlet atenda. Com estas configurações estamos definindo que o
	 * servlet do SpringMVC atenderá as requisições a partir da raiz do nosso
	 * projeto (/).
	 * 
	 **/
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/**
	 * Resolvendo o problema de encoding da aplicação utilizando filtros...
	 * 
	 * método usado pelo Spring que espera receber um array de filtros. Então
	 * vamos criar um CharacterEncodingFilter, definir o encoding deste filtro
	 * usando o valor "UTF-8", adicionar este filtro ao array de filtros e o
	 * retornar esse array para o Spring.
	 * 
	 **/
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}
