/**
 * 
 */
package com.khaledansary.auth.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Khaled
 * WT service will deal with the creation and verification of the Tokens
 */
public class JWTService {
	 private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 5; // 5 days
     private String secret = "scret key";
     private String tokenPrefix = "Bearer";
     private String headerString = "Authorization";
     
     //generate web token with expiration time
     public void addAuthentication(HttpServletResponse response, String username) {
         String JWT = Jwts.builder()
             .setSubject(username)
             .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
             .signWith(SignatureAlgorithm.HS512, secret)
             .compact();
         response.addHeader(headerString, tokenPrefix + " " + JWT);
     }
     
     //parse the token to retrieve a user
     public Authentication getAuthentication(HttpServletRequest request) {
         String token = request.getHeader(headerString);
         if (token != null) {
             String username = Jwts.parser()
                 .setSigningKey(secret)
                 .parseClaimsJws(token)
                 .getBody()
                 .getSubject();
             if (username != null) 
             {
                 return new AuthenticatedUser(username);
             }
         }
         return null;
     }
}
