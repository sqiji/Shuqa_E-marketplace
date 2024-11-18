package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.gcu.business.LoginService;
import com.gcu.business.ProductService;
import com.gcu.business.RegistrationService;

/**
 * Spring Config file
 */
@Configuration
public class SpringConfiguration {
	
	/**
	 * Creates the RegistrationBean
	 * @return The bean used for RegistrationService
	 */
	@Bean(name="registrationService")
	@SessionScope
	public RegistrationService getRegistrationService() {
		//Return an instance for RegistrationService
		return new RegistrationService();
	}
	
	/**
	 * Creates the LoginService
	 * @return The bean used for LoginService
	 */
	@Bean(name="loginService")
	@SessionScope
	public LoginService getLoginService() {
		return new LoginService();
	}
	
	/**
	 * Creates the ProductService for each request
	 * @return The bean used for ProductService
	 */
	@Bean(name="productService")
	@SessionScope
	public ProductService getProductService() {
		return new ProductService();
	}
	
}
