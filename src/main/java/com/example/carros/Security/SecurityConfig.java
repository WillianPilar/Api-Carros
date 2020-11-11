package com.example.carros.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter { //Configurações do Spring Security
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests().anyRequest()
		.authenticated().and()
		.formLogin().and()
		.httpBasic().and()
		.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//Autenticação em memória
		auth
		 .inMemoryAuthentication().passwordEncoder(encoder)
		 .withUser("user").password(encoder.encode("user")).roles("USER").and()
		 .withUser("admin").password(encoder.encode("admin")).roles("USER","ADMIN");
	}
}
