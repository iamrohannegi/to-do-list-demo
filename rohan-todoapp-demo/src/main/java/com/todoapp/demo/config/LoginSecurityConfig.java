package com.todoapp.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource loginDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(loginDataSource);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		
		http.authorizeRequests()
			.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/loginPage")
			.loginProcessingUrl("/authenticateUser")
			.permitAll()
		.and()
		.logout().permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/register/**")
					.antMatchers("/resources/**");
	}
	
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(loginDataSource);
		
		return jdbcUserDetailsManager;
	}
}

