/**
 * 
 */
package com.khaledansary.auth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Khaled
 * This class will intercept POST request on the /login path, and attempt to
 * authenticate users. When the user is successfully authenticated, it will return a JWT
 * in the Authorization header
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
    private JWTService jwtService;
    
    /*specify on which url this filter should act
     * AuthenticationManager use to verify the user details match with the existing user 
     */
    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
         super(new AntPathRequestMatcher(url));
         setAuthenticationManager(authenticationManager);
         jwtService = new JWTService();
    }

    // verify the details match with an existing user
	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws AuthenticationException, IOException, ServletException {
		
		 LoginCredentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), LoginCredentials.class);
	     UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
	     return getAuthenticationManager().authenticate(token);
	}
	
	/* in case of successfully match with the user credentials we will fatch the nam
	 * from the authenticated user and pass to the JWTService
	 */
	 @Override
	 protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
			 throws IOException, ServletException {
		 
	     String name = authentication.getName();
	     jwtService.addAuthentication(response, name);
	 }
}
