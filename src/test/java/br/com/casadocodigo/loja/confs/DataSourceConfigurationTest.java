package br.com.casadocodigo.loja.confs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {

	@Bean
    @Profile("test")
    public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("f10121426");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigohomologacao");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
    }

}
