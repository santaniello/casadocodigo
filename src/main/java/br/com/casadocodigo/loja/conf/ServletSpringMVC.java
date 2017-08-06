package br.com.casadocodigo.loja.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * O getServletConfigClasses pede um array de classes de configurações. 
	 * **/
	@Override
	protected Class<?>[] getServletConfigClasses() {
	    return new Class[] {AppWebConfiguration.class, JPAConfiguration.class};
	}
	
	/** 
	 *  O getServletMappings pede um array com os mapeamentos que queremos que nosso servlet atenda.
	 *  Com estas configurações estamos definindo que o servlet do SpringMVC atenderá as requisições 
	 *  a partir da raiz do nosso projeto (/).
	 *  
	 * **/
	@Override
	protected String[] getServletMappings() {
	    return new String[] {"/"};
	}
}
