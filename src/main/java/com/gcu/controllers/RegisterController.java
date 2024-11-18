package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.business.RegistrationService;
import com.gcu.business.UserBusinessService;
import com.gcu.model.UserModel;

import jakarta.validation.Valid;

/**
 * Controller for the Register route
 */
@Controller
public class RegisterController {

	@Autowired UserBusinessService userService;
	
	//Declare variable for RegistrationService (Instance)
	@Autowired
	private RegistrationService register;
	
	/**
	 * Displays the registration page
	 * @param model Model used in the page generation
	 * @return Routing to the register.html page
	 */
	@GetMapping("/register")
	public String display(Model model) {
		model.addAttribute("title", "Register Form");
		model.addAttribute("userModel", new UserModel());
		
		return "register";
	}
	
	/**
	 * Uses validated form info to create a UserModel for registration
	 * @param userModel UserModel that is created
	 * @param bindingResult Used in data validation
	 * @param model Model for page generation
	 * @return Link to login page, or registration is data is not valid
	 */
	@PostMapping("/doRegister")
	public String doRegister(@Valid UserModel userModel, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "Register Form");
			return "register";
		}
		//System.out.println(userModel.getPassword());
		userModel.setPassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));
		System.out.println(userModel.getPassword());
		register.register(userModel);
		
		return "redirect:/login";
	}
}
