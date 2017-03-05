package com.khaledansary.auth.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Khaled
 * Configure and enable web security to access / and /login route by everyone, 
 * the authentication is required for other routes
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching and cross-site request forgery (csrf)
        http.headers().cacheControl();
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            // filter the api/login requests
            .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            // filter other requests to check the presence of JWT in header
            .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
	
	// Create a default account for test
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	     
	     auth.inMemoryAuthentication()
	         .withUser("admin")
	         .password("password")
	         .roles("ADMIN");
	 }
}
