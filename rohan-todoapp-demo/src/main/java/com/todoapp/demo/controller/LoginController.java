package com.todoapp.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

	//show custom login-page
	@GetMapping("/loginPage")
	public String showLoginPage() {
		return "custom-login";
	}
	
	
}
