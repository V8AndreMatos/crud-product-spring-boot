package com.crud.product;

import org.springframework.boot.SpringApplication;

public class TestCrudSpringProductApplication {

	public static void main(String[] args) {
		SpringApplication.from(CrudSpringProductApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
