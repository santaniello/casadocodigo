package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * O getServletConfigClasses pede um array de classes de configurações.
	 **/
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppWebConfiguration.class, JPAConfiguration.class };
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
	 * método  usado pelo Spring que espera receber um array de filtros. 
	 * Então vamos criar um  CharacterEncodingFilter, definir o encoding
	 * deste filtro usando  o valor "UTF-8", adicionar este filtro ao 
	 * array de filtros e o retornar esse array para o Spring.
	 * 
	 **/
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}

}
