package org.bpmn.training.camundatraining;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;


@ExtendWith(ProcessEngineExtension.class)
class CamundaTrainingApplicationTests {

	ProcessEngine processEngine;

	@Test
	@Deployment(resources = "tweetQA.bpmn")
    void shouldTestHappyPathOfBPMN() {
		RuntimeService runtimeService = processEngine.getRuntimeService();

		// Create a HashMap to put in variables for the process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", true);
		// Start process with Java API and variables
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("twitterQa", variables);

		// Make assertions on the process instance
		assertThat(processInstance).isEnded();
    }

}
