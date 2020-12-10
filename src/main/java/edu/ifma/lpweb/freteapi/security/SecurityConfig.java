package edu.ifma.lpweb.freteapi.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	

	@Override //Configura a autenticacao
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	

	@Override //Configura a autorizacao
	protected void configure(HttpSecurity http) throws Exception {
		// autenticação com sessão
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/clientes").permitAll()
		.antMatchers(HttpMethod.GET, "/clientes/*").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();

		//autenticação stateless
/*
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/clientes").permitAll()
				.antMatchers(HttpMethod.GET, "/clientes/*").permitAll()
				.anyRequest().authenticated()
				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
*/

	}
	
	@Override //Configuracoes de recursos estaticos(html, css, imagens, ...)
	public void configure(WebSecurity web) throws Exception {
	}

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("secreta"));
	}

	
}
