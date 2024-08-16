package org.bpmn.training.camundatraining;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamundaSpringBootDeployWithUnitTestCoverage {

    public static void main(String[] args) {
		SpringApplication.run(CamundaSpringBootDeployWithUnitTestCoverage.class, args);
	}

}
