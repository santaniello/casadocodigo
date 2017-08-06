package br.com.casadocodigo.loja.conf;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe que será usada como classe de configuração de Testes do JPA.
 */

@Configuration
@EnableTransactionManagement 
@ComponentScan(basePackages={"br.com.casadocodigo.loja.daos","br.com.casadocodigo.loja.repositories"})
/** 
 * Configurando o Spring Data...
 * 
 * A configuração abaixo será substituida
 * 
 * <jpa:repositories base-package="br.com.opentask.repositories"
                     entity-manager-factory-ref="entityManagerFactory"
	                 transaction-manager-ref="transactionManager"/>
 * 
 * 
 * */ 
@EnableJpaRepositories(basePackages = {"br.com.casadocodigo.loja.repositories"}, 
										entityManagerFactoryRef="entityManagerFactory", 
										transactionManagerRef="transactionEntityManager")
public class JPATestConfiguration {

	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();		
		em.setPackagesToScan(new String[] {"br.com.casadocodigo.loja.models" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("f10121426");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigohomologacao");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		em.setDataSource(dataSource);
				
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		em.setJpaPropertyMap(properties);

		return em;
	}
	
	@Bean(name="transactionEntityManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}