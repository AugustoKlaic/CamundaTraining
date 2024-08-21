package org.bpmn.training.springcamundamicroserviceorchestration;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import org.bpmn.training.springcamundamicroserviceorchestration.jobworker.PaymentProcessManualWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class ManualProcessor {

    @Value("${zeebe.client.cloud.authServerUrl}")
    private String zeebAuthorizationServer;
    @Value("${zeebe.client.cloud.tokenAudience}")
    private String zeebTokenAudience;
    @Value("${zeebe.client.cloud.clientId}")
    private String zeebClientId;
    @Value("${zeebe.client.cloud.clientSecret}")
    private String zeebClientSecret;
    @Value("${zeebe.client.cloud.address}")
    private String zeebAddress;

    private final PaymentProcessManualWorker paymentProcessManualWorker;

    public ManualProcessor(PaymentProcessManualWorker paymentProcessManualWorker) {
        this.paymentProcessManualWorker = paymentProcessManualWorker;
    }

    // This is for manually setting the server and completing the task as is done in pure java camunda tutorial
    public void executeManualProcess() {
        final OAuthCredentialsProvider credentialsProvider = new OAuthCredentialsProviderBuilder()
                .authorizationServerUrl(zeebAuthorizationServer)
                .audience(zeebTokenAudience)
                .clientId(zeebClientId)
                .clientSecret(zeebClientSecret)
                .build();

        try (final ZeebeClient client = ZeebeClient.newClientBuilder()
                .gatewayAddress(zeebAddress)
                .credentialsProvider(credentialsProvider)
                .build()) {

            // Created new instance in the BPMN with this variables
            final Map<String, Object> variables = new HashMap<>();
            variables.put("reference", "C8_12345");
            variables.put("amount", 100.00);
            variables.put("cardNumber", "1234567812345678");
            variables.put("cardExpiry", "12/2023");
            variables.put("cardCVC", "123");

            client.newCreateInstanceCommand().bpmnProcessId("paymentProcess")
                    .latestVersion()
                    .variables(variables)
                    .send()
                    .join();

            final JobWorker creditCardWorker =
                    client.newWorker()
                            .jobType("chargeCreditCard")
                            .handler(paymentProcessManualWorker)
                            .timeout(Duration.ofSeconds(5).toMillis())
                            .open();

            Thread.sleep(5000);
            creditCardWorker.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
