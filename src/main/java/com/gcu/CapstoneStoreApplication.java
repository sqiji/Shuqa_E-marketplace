package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.unit.DataSize;

import jakarta.servlet.MultipartConfigElement;

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

	@Bean
public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxFileSize(DataSize.ofMegabytes(10));  // Max file size
    factory.setMaxRequestSize(DataSize.ofMegabytes(20)); // Max request size
    return factory.createMultipartConfig();
}

}
