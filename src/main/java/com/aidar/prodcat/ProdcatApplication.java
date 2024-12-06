package com.aidar.prodcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ProdcatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdcatApplication.class, args);
	}

}
