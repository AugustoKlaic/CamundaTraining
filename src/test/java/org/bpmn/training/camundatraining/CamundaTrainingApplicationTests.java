package org.bpmn.training.camundatraining;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
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

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;


@ExtendWith(ProcessEngineCoverageExtension.class)
@Deployment(resources = {"tweetApproval.dmn", "tweetQA.bpmn"})
class CamundaTrainingApplicationTests {

    private final static String MANAGEMENT_GROUP = "management";

    private ProcessEngine processEngine;

    @Test
    void shouldTestHappyPathOfBPMN() {
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("content", "First tweet!");
        variables.put("email", "approved@gmail.com");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("twitterQa", variables);

        BpmnAwareTests.assertThat(processInstance).isWaitingAt(findId("post tweet"));

        List<Job> jobList = jobQuery()
                .processInstanceId(processInstance.getId())
                .list();

        assertThat(jobList).hasSize(1);
        Job job = jobList.get(0);

        execute(job);

        BpmnAwareTests.assertThat(processInstance).isEnded();
    }

    @Test
    void shouldTestTweetRejected() {
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("content", "First tweet!");
        variables.put("approved", false);

        ProcessInstance processInstance = runtimeService.createProcessInstanceByKey("twitterQa")
                .setVariables(variables)
                .startAfterActivity(findId("review tweet"))
                .execute();

        BpmnAwareTests.assertThat(processInstance)
                .isWaitingAt(findId("delete tweet"))
                .externalTask()
                .hasTopicName("notification");

        complete(externalTask());

        BpmnAwareTests.assertThat(processInstance).isEnded().hasPassed(findId("delete tweet"));

    }

    @Test
    void shouldTestTweetSuperUser() {
        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService
                .createMessageCorrelation("superuserTweet")
                .setVariable("content", "My superUser tweet!")
                .correlateWithResult()
                .getProcessInstance();
        ;

        BpmnAwareTests.assertThat(processInstance).isStarted();

        List<Job> jobList = jobQuery()
                .processInstanceId(processInstance.getId())
                .list();

        assertThat(jobList).hasSize(1);
        Job job = jobList.get(0);
        execute(job);

        BpmnAwareTests.assertThat(processInstance).isEnded();
    }

    @Test
    void shouldTestTweetWithdrawn() {
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<>();
        variables.put("content", "duplicate tweet error");
        variables.put("email", "approved@gmail.com");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("twitterQa", variables);

        BpmnAwareTests.assertThat(processInstance).isWaitingAt(findId("post tweet"));

        List<Job> jobList = jobQuery()
                .processInstanceId(processInstance.getId())
                .list();

        assertThat(jobList).hasSize(1);
        Job job = jobList.get(0);
        execute(job);

        BpmnAwareTests.assertThat(processInstance).isWaitingAt(findId("edit tweet"));

        runtimeService()
                .createMessageCorrelation("tweetWithdrawn")
                .processInstanceVariableEquals("content", "duplicate tweet error")
                .correlateWithResult();

        BpmnAwareTests.assertThat(processInstance).isEnded();
    }

    @Test
    void shouldTestTweetEditAndApprove() {
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<>();
        variables.put("content", "duplicate tweet error");
        variables.put("email", "approved@gmail.com");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("twitterQa", variables);

        BpmnAwareTests.assertThat(processInstance).isWaitingAt(findId("post tweet"));

        List<Job> jobList = jobQuery()
                .processInstanceId(processInstance.getId())
                .list();

        assertThat(jobList).hasSize(1);
        Job job = jobList.get(0);
        execute(job);

        BpmnAwareTests.assertThat(processInstance).isWaitingAt(findId("edit tweet"));

        List<Task> taskList = taskService()
                .createTaskQuery()
                .taskCandidateGroup(MANAGEMENT_GROUP)
                .processInstanceId(processInstance.getId())
                .list();

        assertThat(taskList).isNotNull();
        assertThat(taskList).hasSize(1);

        Task task = taskList.get(0);
        taskService().complete(task.getId(), Map.of("content", "Edited tweet"));

        BpmnAwareTests.assertThat(processInstance).isWaitingAt(findId("post tweet"));

        List<Job> secondJobList = jobQuery()
                .processInstanceId(processInstance.getId())
                .list();

        assertThat(secondJobList).hasSize(1);
        Job secondJob = secondJobList.get(0);
        execute(secondJob);

        BpmnAwareTests.assertThat(processInstance).isEnded();
    }

    @Test
    void shouldTestTweetApprovalEmailApproved() {

        Map<String, Object> variables = withVariables("email", "approved@gmail.com", "content", "tweet posted");
        DmnDecisionTableResult decisionResult = decisionService().evaluateDecisionTableByKey("tweetApproval", variables);

        assertThat(decisionResult.getFirstResult()).contains(entry("approved", true));

    }
}
