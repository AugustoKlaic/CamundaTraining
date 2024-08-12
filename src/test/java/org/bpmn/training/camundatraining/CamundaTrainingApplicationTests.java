package org.bpmn.training.camundatraining;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.extension.process_test_coverage.junit5.ProcessEngineCoverageExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;


@ExtendWith(ProcessEngineCoverageExtension.class)
class CamundaTrainingApplicationTests {

	private final static String MANAGEMENT_GROUP = "management";

	ProcessEngine processEngine;

	@Test
	@Deployment(resources = "tweetQA.bpmn")
    void shouldTestHappyPathOfBPMN() {
		RuntimeService runtimeService = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("content", "First tweet!");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("twitterQa", variables);

		BpmnAwareTests.assertThat(processInstance).isWaitingAt(findId("review tweet"));

		List<Task> taskList = taskService()
				.createTaskQuery()
				.taskCandidateGroup(MANAGEMENT_GROUP)
				.processInstanceId(processInstance.getId())
				.list();

		assertThat(taskList).isNotNull();
		assertThat(taskList).hasSize(1);

		Task task = taskList.get(0);
		taskService().complete(task.getId(), Map.of("approved", true));

		List<Job> jobList = jobQuery()
				.processInstanceId(processInstance.getId())
				.list();

		assertThat(jobList).hasSize(1);
		Job job = jobList.get(0);

		execute(job);

		BpmnAwareTests.assertThat(processInstance).isEnded();
    }

}
