package org.bpmn.training.springcamundamicroserviceorchestration.jobworker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import org.bpmn.training.springcamundamicroserviceorchestration.exception.CreditCardServiceException;
import org.bpmn.training.springcamundamicroserviceorchestration.exception.InvalidCreditCardException;
import org.bpmn.training.springcamundamicroserviceorchestration.service.CreditCardService;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentProcessManualWorker implements JobHandler {

    private static final String BPMN_ERROR_INVALID_CARD_EXPIRY_DATE = "cardExpiryDateError";
    private final CreditCardService creditCardService;

    public PaymentProcessManualWorker(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        try {
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
        } catch (CreditCardServiceException e) {
            // error handling
            Integer retries = job.getRetries();
            client.newFailCommand(job.getKey()).retries(retries - 1)
                    .retryBackoff(Duration.ofSeconds(10))
                    .errorMessage(e.getMessage())
                    .send()
                    .join();
        } catch (InvalidCreditCardException e) {
            client.newThrowErrorCommand(job.getKey())
                    .errorCode(BPMN_ERROR_INVALID_CARD_EXPIRY_DATE)
                    .send()
                    .join();
        }
    }
}
