package org.bpmn.training.springcamundamicroserviceorchestration.jobworker;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessWorker {

    @JobWorker
    public void chargeCreditCard() {
        System.out.println("EXECUTADO");
    }
}
