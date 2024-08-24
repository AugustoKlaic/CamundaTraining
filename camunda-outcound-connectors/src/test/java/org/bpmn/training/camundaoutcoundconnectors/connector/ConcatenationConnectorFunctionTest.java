package org.bpmn.training.camundaoutcoundconnectors.connector;

import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ConcatenationConnectorFunctionTest {

    private String input1;
    private String input2;

    @BeforeEach
    void setup() {
        input1 = "input1";
        input2 = "input2";
    }

    @Test
    void should() throws Exception {
        ConcatenationConnectorRequest connectorRequest = new ConcatenationConnectorRequest(input1, input2);

        ConcatenationConnectorFunction connectorFunction = new ConcatenationConnectorFunction(connectorRequest);

        OutboundConnectorContext context = OutboundConnectorContextBuilder.create().variables(connectorRequest).build();

        Object result = connectorFunction.execute(context);

        assertThat(result)
                .isInstanceOf(ConcatenationConnectorResult.class)
                .extracting("concatenationResult")
                .isEqualTo("input1 input2");
    }
}