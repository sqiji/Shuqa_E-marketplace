package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application file
 */
@SpringBootApplication
@ComponentScan(basePackages="com.gcu")
public class CapstoneStoreApplication {
	
	/**
	 * Main function ran
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CapstoneStoreApplication.class, args);
	}

}
