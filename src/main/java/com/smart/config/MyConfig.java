package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {
	
	
	  @Bean
	  public UserDetailsService getUserDetailService() {
	  
	  
	  return new UserDetailsServiceImpl();
	  
	  }
	 

	  @Bean 
	  public BCryptPasswordEncoder passwordEncoder() {

	  return new BCryptPasswordEncoder();
	  
	  }
	 
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	
	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	
	
	daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
	
	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	
	
	return daoAuthenticationProvider;

	
	
	}

	
	  @Override 
	  protected void configure(AuthenticationManagerBuilder auth) throws
	  Exception {
	  
	  auth.authenticationProvider(authenticationProvider());
	  
	  }
	 
	
	 

	  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/home").permitAll()
		.and()
		.formLogin()	  
		.loginPage("/signin") // This is where the login page is served from, not where the form is submitted to
	    .loginProcessingUrl("/dologin") // This is where the form should be submitted to. 
	    .defaultSuccessUrl("/user/index")
		//.failureUrl("/login_fail")
				 
				 
				 
		
		.and()
		.csrf().disable();
		
		
	}
	
	
	
	
}
