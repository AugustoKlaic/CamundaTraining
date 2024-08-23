package org.bpmn.training.camundaoutcoundconnectors.connector;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;

@OutboundConnector(
        name = "concatenation-connector",
        inputVariables = {"input1", "input2"},
        type = "io.camunda:concatenation-api:1")
public class ConcatenationConnectorFunction implements OutboundConnectorFunction {

    private final ConcatenationConnectorRequest connectorRequest;

    public ConcatenationConnectorFunction(ConcatenationConnectorRequest connectorRequest) {
        this.connectorRequest = connectorRequest;
    }

    @Override
    public Object execute(OutboundConnectorContext context) throws Exception {
        final var connectorRequest = context.bindVariables(ConcatenationConnectorRequest.class);
        return executeConnector(connectorRequest);
    }

    private ConcatenationConnectorResult executeConnector(final ConcatenationConnectorRequest connectorRequest) {
        String concatenationResult = connectorRequest.getInput1() + " " +connectorRequest.getInput2();
        var result = new ConcatenationConnectorResult(concatenationResult);
        return result;
    }
}
