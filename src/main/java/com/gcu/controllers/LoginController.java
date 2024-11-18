package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.business.LoginService;	
import com.gcu.model.LoginModel;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * Controller for the Login page
 */
@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	/**
	 * Displays the logic page
	 * @param model Model for rendering the page
	 * @return Routing to the logic page
	 */
	@GetMapping("/login")
	public String display(Model model)
	{
		model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
        
        return "login";
	}
	
	/**
	 * Verifies the login information and logs in if valid
	 * @param loginModel Model containing the login information
	 * @param bindingResult Verifies the login information
	 * @param model Model for displaying the page
	 * @param session Used for remembering login
	 * @return Either routing back to the Login page or to the products
	 */ 
	@PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
    {
    	if(bindingResult.hasErrors())
    	{
    		model.addAttribute("title", "Login Form");	
    		return "login"; 
    	}
    	
    	boolean success = loginService.login(loginModel);
    	
    	if(!success) {
    		
    		model.addAttribute("title", "Login Form");
    		model.addAttribute("loginFailed", true);
    		return "login";	
    		
    	}
    	
    	return "redirect:/myproducts";
    }
}
