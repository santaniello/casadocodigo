package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe que será usada como classe de configuração do JPA.
 */

@EnableTransactionManagement // Habilitando o controle de transação do Spring...
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
public class JPAConfiguration {
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		
		/*	Substituindo a seguinte parte do XML de configuração do Spring...
		 * 
		 * 	<!-- Injetando e gerenciando o entity manager -->
			<beans:bean id="entityManagerFactory"
				class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
				<beans:property name="dataSource" ref="dsProducao" />
				<beans:property name="jpaVendorAdapter">
					<beans:bean
						class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" />
				</beans:property>
			</beans:bean>
		 * 
		 * 
		 * */
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		factoryBean.setDataSource(dataSource);
		
		factoryBean.setJpaProperties(aditionalProperties());

		// Setando as entitidades....	
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");

		return factoryBean;
	}
	
	/*
	 *<!-- Criando um datasource -->
	  <beans:bean id="dsProducao" class="org.springframework.jdbc.datasource.DriverManagerDataSource"> 
		  <beans:property name="driverClassName" value="com.mysql.jdbc.Driver" /> 
		  <beans:property name="url" value="jdbc:mysql://localhost:3306/srmdb?autoReconnect=true" /> 
		  <beans:property name="username" value="root" /> 
		  <beans:property name="password" value="f10121426" /> 
	  </beans:bean>
	*/ 
	
	
	@Bean
	@Profile("dev")
	public DataSource dataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setUsername("root");
	    dataSource.setPassword("f10121426");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    return dataSource;
	}
	
	
	/*
	 * Substtitui o arquivo  persistence.xml com as configurações do hibernate
	 * 
	 *  <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true" />
    	    <property name="hibernate.hbm2ddl.auto" value="update" /> 
    	</properties>
	 * 
	 *  
	 * */
	
	private Properties aditionalProperties(){
	    Properties props = new Properties();
	    props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    props.setProperty("hibernate.show_sql", "true");
	    props.setProperty("hibernate.hbm2ddl.auto", "update");
	    return props;
	}
	
	

	/** Criando o gerenciador de transações ...
	 * 
	 * <!-- Controlando transações com spring -->
	 * 
	 *	<beans:bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
	 *		<beans:property name="entityManagerFactory" ref="entityManagerFactory" />
	 *	</beans:bean>
	 * 
	 * 
	 * */	
	@Bean(name="transactionEntityManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
	
	
	
}
