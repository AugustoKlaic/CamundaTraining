package org.bpmn.training.camundatraining;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamundaTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaTrainingApplication.class, args);
	}

}
