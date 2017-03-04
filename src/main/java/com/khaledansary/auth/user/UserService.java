package com.khaledansary.auth.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private List<User> users = new ArrayList<>(Arrays.asList(
			new User(1,"User01","user01@example.com","/image/01.jpg"),
			new User(2,"User02","user02@example.com","/image/02.jpg"),
			new User(3,"User03","user03@example.com","/image/03.jpg"),
			new User(4,"User04","user01@example.com","/image/04.jpg")
			));
	
	public List<User> getUsers(){
		return users;
	}

}
