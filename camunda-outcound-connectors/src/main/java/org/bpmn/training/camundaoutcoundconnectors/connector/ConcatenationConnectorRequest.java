package org.bpmn.training.camundaoutcoundconnectors.connector;

import jakarta.validation.constraints.NotEmpty;

public class ConcatenationConnectorRequest {

    @NotEmpty
    String input1;

    @NotEmpty
    String input2;

    public String getInput1() {
        return input1;
    }

    public String getInput2() {
        return input2;
    }
}
