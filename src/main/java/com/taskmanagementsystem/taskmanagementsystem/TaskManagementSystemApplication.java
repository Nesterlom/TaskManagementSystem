package com.taskmanagementsystem.taskmanagementsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "TaskManagementSystem"))
public class TaskManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementSystemApplication.class, args);
	}

}

//ADMIN can create/update new tasks and set workers to task,
// worker who work on some task can change it status and write comments
//add author to task(implement throw Principal), also change db
//Swagger docs
//docker compose
//unit tests