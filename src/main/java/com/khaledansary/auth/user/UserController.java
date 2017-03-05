package com.khaledansary.auth.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Khaled
 * 
 */
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public List<User> getUsers(){
		return userService.getUsers();		
	}
	
	@RequestMapping("/users/{id}")
	public User getTopic(@PathVariable String id){
		return userService.getUser(id);
	}
}
