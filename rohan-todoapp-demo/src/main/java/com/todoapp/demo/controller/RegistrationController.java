package com.todoapp.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todoapp.demo.entity.UserInfo;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserDetailsManager userDetailsManager;

	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		//Trim trailing white-spaces in strings and trim empty strings to null
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/registrationForm")
	public String showRegistrationPage(Model model) {
		model.addAttribute("newUser", new UserInfo());
		return "registration-form";
	}

	@PostMapping("/processRegistration")
	public String processRegistration(@Valid @ModelAttribute("newUser") UserInfo user,
								 	  BindingResult bindingResult, Model model) {
		// form validation
		if(bindingResult.hasErrors()) {
			//return the user details entered and registrationError to show at the top of form
			model.addAttribute("newUser", user);
			model.addAttribute("registrationError", "UserName/Password cannot be empty.");
			
			return "registration-form";
		}
		
		//check the database if user already exists.
		if(doesUserExists(user.getUserName())) {
			//if already exists return to registration page with error.			
			model.addAttribute("newUser", user);
			model.addAttribute("registrationError","User Name already exists.");
			
			return "registration-form";
		}
		
		// encrypt the password.
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		// add the encoding algorithm id {bcrypt}
		encodedPassword = "{bcrypt}" + encodedPassword;
		
		//give user role
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
		
		//create user details object
		User tempUser = new User(user.getUserName(), encodedPassword, authorities);
		
		//save user in the database
		userDetailsManager.createUser(tempUser);
		
		
		//return to registration page with success message
		model.addAttribute("registrationSuccessful", "Registered Successfully.");
		return "registration-form";
	}
	
	
	//Check if user name already exists in the database.
	private boolean doesUserExists(String userName) {
		return userDetailsManager.userExists(userName);
	}

}
