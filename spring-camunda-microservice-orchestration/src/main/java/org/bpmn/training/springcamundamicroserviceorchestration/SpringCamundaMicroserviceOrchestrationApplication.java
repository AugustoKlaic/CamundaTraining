package org.bpmn.training.springcamundamicroserviceorchestration;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
public class SpringCamundaMicroserviceOrchestrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCamundaMicroserviceOrchestrationApplication.class, args);
	}

}
