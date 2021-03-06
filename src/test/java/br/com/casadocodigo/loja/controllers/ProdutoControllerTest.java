package br.com.casadocodigo.loja.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;
import br.com.casadocodigo.loja.confs.DataSourceConfigurationTest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class,SecurityConfiguration.class})
@ActiveProfiles("test")
public class ProdutoControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	
	// Filtro do Spring security...
	@Autowired
	private Filter springSecurityFilterChain;

	private MockMvc mockMvc;
	
	@Before
	public void setup(){		
	   mockMvc = MockMvcBuilders
	    		.webAppContextSetup(wac)
	    		.addFilter(springSecurityFilterChain) //registrando o filtro do spring security...
	    		.build();
	}
	
	
	@Test
	public void deveRetornarParaHomeComOsLivros() throws Exception {
		 //verifica a url /
		 mockMvc.perform(MockMvcRequestBuilders.get("/"))
		 // verifica se dentro do ModelAndView existe o aributo produtos...
         .andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
         // verifica se retorna a jsp...        
         .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
	}
	
	// Testando a autenticação do spring security...
	
	@Test
	public void somenteAdminDeveAcessarProdutosForm() throws Exception{
	    mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
	            .with(SecurityMockMvcRequestPostProcessors
	                .user("user@casadocodigo.com.br").password("123456")
	                .roles("USUARIO")))
	            .andExpect(MockMvcResultMatchers.status().is(403));
	}
	
}
