package org.bpmn.training.springcamundamicroserviceorchestration;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
public class SpringCamundaMicroserviceOrchestrationApplication implements CommandLineRunner {

    @Value("${zeebe.client.manual.configuration.enable}")
    private Boolean manualExecutionEnabled;

    private final ManualProcessor manualProcessor;
    private final SpringManagedProcessor springManagedProcessor;

    public SpringCamundaMicroserviceOrchestrationApplication(ManualProcessor manualProcessor, SpringManagedProcessor springManagedProcessor) {
        this.manualProcessor = manualProcessor;
        this.springManagedProcessor = springManagedProcessor;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCamundaMicroserviceOrchestrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (manualExecutionEnabled) {
            manualProcessor.executeManualProcess();
        } else {
            springManagedProcessor.executeSpringManagedProcess();
        }
    }
}
