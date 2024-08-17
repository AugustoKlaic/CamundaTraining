package org.bpmn.training.springcamundamicroserviceorchestration.jobworker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;

public class PaymentProcessManualWorker implements JobHandler {

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        System.out.println("EXECUTADO MANUAL");
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
