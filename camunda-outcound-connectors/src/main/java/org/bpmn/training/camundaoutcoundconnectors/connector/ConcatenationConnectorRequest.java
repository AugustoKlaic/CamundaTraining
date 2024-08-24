package org.bpmn.training.camundaoutcoundconnectors.connector;

import jakarta.validation.constraints.NotEmpty;

public class ConcatenationConnectorRequest {

    @NotEmpty
    private String input1;

    @NotEmpty
    private String input2;

    public ConcatenationConnectorRequest(String input1, String input2) {
        this.input1 = input1;
        this.input2 = input2;
    }

    public ConcatenationConnectorRequest() {
    }

    public String getInput1() {
        return input1;
    }

    public String getInput2() {
        return input2;
    }
}
