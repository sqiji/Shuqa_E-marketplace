package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController class the controller for the home page 
 * (index.html) route
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	/**
	 * Display the home page (index.html)
	 * @param model use in the page generation
	 * @return routing to the index.html (home page)
	 */
	@GetMapping("/")
	public String display(Model model) {
		model.addAttribute("title", "");
		
		return "index";
	}
	
	
	@GetMapping("/forgetpassword")
	public String forgetPassword(Model model) {
model.addAttribute("title", "");
		
		return "forgetpassword";
	}
}
