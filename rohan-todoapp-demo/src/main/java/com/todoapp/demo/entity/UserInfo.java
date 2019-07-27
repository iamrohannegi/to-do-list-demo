package com.todoapp.demo.entity;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserInfo {
	
	@NotNull(message= " is required")
	@Size(min=1, message=" is required")
	@Size(max=50, message=" can't be greater than 50 characters.")
	private String userName;
	
	@NotNull(message= " is required")
	@Size(min=1, message=" is required")
	@Size(max=68, message=" can't be greater than 60 characters.")
	private String password;
	
	public UserInfo() {}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
