package com.spicy.kitchen.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication()
        .withUser("user").password("{noop}password").roles("USER")
        .and()
        .withUser("admin").password("{noop}password").roles("ADMIN")
		.and()
		.withUser("manager").password("{noop}manager").roles("USER","MANAGER","ADMIN");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
        //HTTP Basic authentication
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/menu/**").hasRole("USER")
       .antMatchers(HttpMethod.GET,"/memu").hasRole("USER")
       .antMatchers(HttpMethod.POST, "/menu").hasRole("ADMIN")
       .antMatchers(HttpMethod.PUT, "/menu/**").hasRole("ADMIN")
       .antMatchers(HttpMethod.DELETE, "/menu/**").hasRole("ADMIN")
        .and()
        .csrf().disable()
        .formLogin().disable();
		http.headers().frameOptions().disable();

}
}
