package org.bpmn.training.springcamundamicroserviceorchestration;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SpringManagedProcessor {

    private final ZeebeClient zeebeClient;

    public SpringManagedProcessor(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    public void executeSpringManagedProcess() {

        // Created new instance in the BPMN with this variables
        final Map<String, Object> variables = new HashMap<>();
        variables.put("reference", "C8_12345");
        variables.put("amount", 100.00);
        variables.put("cardNumber", "1234567812345678");
        variables.put("cardExpiry", "12/2023");
        variables.put("cardCVC", "123");

        zeebeClient
                .newCreateInstanceCommand()
                .bpmnProcessId("paymentProcess")
                .latestVersion()
                .variables(variables)
                .send()
                .join();
    }
}
