package com.khaledansary.auth.user;

public class User {
	
	private Integer id;
	private String name;
	private String email;
	private String picture;
	
	public User(){
		
	}
	
	public User(Integer id, String name, String email, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	

}
