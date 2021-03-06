package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("usuarioDAO")
    private UserDetailsService usuarioDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
	    http.authorizeRequests()
//      .antMatchers("/produtos/form/").hasRole("ADMIN")
	    .antMatchers("/carrinho/**").permitAll()
//	    .antMatchers(HttpMethod.POST, "/produtos/").hasRole("ADMIN")
//	    .antMatchers(HttpMethod.GET, "/produtos/").hasRole("ADMIN")
	    .antMatchers("/produtos/**").hasRole("ADMIN")
	    .antMatchers("/resources/**").permitAll()
	    .antMatchers("/pagamento/**").permitAll()
	    .antMatchers("/").permitAll()
	    // informando que qualquer request tem de estar autenticado...
	    .anyRequest().authenticated()
	    // redireciona para o login...
	    .and().formLogin().loginPage("/login").permitAll()
	   // .defaultSuccessUrl("/")
	    // Fazendo logout na aplicação
	    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDao)
        // informando o tipo de encriptação das senhas dos usuuários...
        .passwordEncoder(new BCryptPasswordEncoder());;
    }

}
