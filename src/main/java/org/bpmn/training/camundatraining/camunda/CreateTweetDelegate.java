package org.bpmn.training.camundatraining.camunda;

import org.bpmn.training.camundatraining.service.TwitterService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

public class CreateTweetDelegate implements JavaDelegate {

    TwitterService twitterService = new TwitterService();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String content = (String) delegateExecution.getVariable("content");

        if (content.equals("Network error")) {
            throw new RuntimeException("simulated network error");
        }

        twitterService.updateStatus(content);
    }
}
