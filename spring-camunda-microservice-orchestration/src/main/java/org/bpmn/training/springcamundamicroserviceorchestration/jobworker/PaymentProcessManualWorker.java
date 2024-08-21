package org.bpmn.training.springcamundamicroserviceorchestration.jobworker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import org.bpmn.training.springcamundamicroserviceorchestration.service.CreditCardService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentProcessManualWorker implements JobHandler {

    private final CreditCardService creditCardService;

    public PaymentProcessManualWorker(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        System.out.println("EXECUTANDO MANUAL");

        Map<String, Object> inputVariables = job.getVariablesAsMap();
        String reference = (String) inputVariables.get("reference");
        Double amount = (Double) inputVariables.get("amount");
        String cardNumber = (String) inputVariables.get("cardNumber");
        String cardExpiry = (String) inputVariables.get("cardExpiry");
        String cardCVC = (String) inputVariables.get("cardCVC");

        String confirmation = creditCardService.chargeCreditCard(reference, amount, cardNumber, cardExpiry, cardCVC);

        Map<String, Object> outputVariables = new HashMap<>();
        outputVariables.put("confirmation", confirmation);

        client.newCompleteCommand(job.getKey()).variables(outputVariables).send().join();
    }
}
