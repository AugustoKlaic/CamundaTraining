package org.bpmn.training.springcamundamicroserviceorchestration.jobworker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.bpmn.training.springcamundamicroserviceorchestration.service.CreditCardService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentProcessWorker {

    private final CreditCardService creditCardService;

    public PaymentProcessWorker(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @JobWorker //Comment this notation for manual process, because it will consume the generated instance
    public void chargeCreditCard(JobClient client, ActivatedJob job) {

        System.out.println("SPRING MANAGED EXECUTADO");

        Map<String, Object> inputVariables = job.getVariablesAsMap();
        String reference = (String) inputVariables.get("reference");
        Double amount = (Double) inputVariables.get("amount");
        String cardNumber = (String) inputVariables.get("cardNumber");
        String cardExpiry = (String) inputVariables.get("cardExpiry");
        String cardCVC = (String) inputVariables.get("cardCVC");

        String confirmation = creditCardService.chargeCreditCard(reference, amount, cardNumber, cardExpiry, cardCVC);

        Map<String, Object> outputVariables = new HashMap<>();
        outputVariables.put("confirmation", confirmation);

        client.newCompleteCommand(job).variables(outputVariables).send();
    }
}
