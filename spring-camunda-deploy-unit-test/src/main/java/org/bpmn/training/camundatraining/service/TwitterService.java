package org.bpmn.training.camundatraining.service;

public class TwitterService {

    public void updateStatus(String content) throws Exception {
        if(content.equals("duplicate tweet error")) {
            throw new Exception("Duplicate tweet error!");
        }

        System.out.println("Tweet: " + content);
    }
}
