package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.king") 
public class MyActivitiDemoApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(MyActivitiDemoApplication.class, args);
	}
	   @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(MyActivitiDemoApplication.class);
	    }
}