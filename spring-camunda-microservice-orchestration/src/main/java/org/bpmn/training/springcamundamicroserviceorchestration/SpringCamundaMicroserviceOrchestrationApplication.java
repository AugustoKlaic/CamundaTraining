package org.bpmn.training.springcamundamicroserviceorchestration;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.bpmn.training.springcamundamicroserviceorchestration.jobworker.PaymentProcessManualWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
@EnableZeebeClient
public class SpringCamundaMicroserviceOrchestrationApplication implements CommandLineRunner {

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

    @Value("${zeebe.client.manual.configuration.enable}")
    private Boolean manualExecutionEnabled;

    public static void main(String[] args) {
        SpringApplication.run(SpringCamundaMicroserviceOrchestrationApplication.class, args);
    }

    // This is for manually setting the server and completing the task as is done in pure java camunda tutorial
    @Override
    public void run(String... args) throws Exception {
        if (manualExecutionEnabled) {
            final OAuthCredentialsProvider credentialsProvider =
                    new OAuthCredentialsProviderBuilder()
                            .authorizationServerUrl(zeebAuthorizationServer)
                            .audience(zeebTokenAudience)
                            .clientId(zeebClientId)
                            .clientSecret(zeebClientSecret)
                            .build();

            try (final ZeebeClient client =
                         ZeebeClient.newClientBuilder()
                                 .gatewayAddress(zeebAddress)
                                 .credentialsProvider(credentialsProvider)
                                 .build()) {

                final JobWorker creditCardWorker =
                        client.newWorker()
                                .jobType("chargeCreditCard")
                                .handler(new PaymentProcessManualWorker())
                                .timeout(Duration.ofSeconds(10).toMillis())
                                .open();
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
