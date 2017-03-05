package com.khaledansary.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Khaled
 * 
 */

@SpringBootApplication
@RestController
public class Login0AuthApplication {

	@RequestMapping("/")
	String hello(){
		return "Hello";
	}
	public static void main(String[] args) {
		SpringApplication.run(Login0AuthApplication.class, args);
	}
}
