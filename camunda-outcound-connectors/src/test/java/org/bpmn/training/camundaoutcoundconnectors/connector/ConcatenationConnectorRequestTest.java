package org.bpmn.training.camundaoutcoundconnectors.connector;

import io.camunda.connector.api.error.ConnectorInputException;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConcatenationConnectorRequestTest {

    private String input1;
    private String input2;

    @BeforeEach
    void setup() {
        input1 = "input1";
        input2 = "input2";
    }

    @Test
    void shouldCreateConnectorRequestWithInputs() {
        ConcatenationConnectorRequest input = new ConcatenationConnectorRequest(input1, input2);
        OutboundConnectorContext context = OutboundConnectorContextBuilder.create().variables(input).build();

        final ConcatenationConnectorRequest connectorRequest = context.bindVariables(ConcatenationConnectorRequest.class);

        assertThat(connectorRequest)
                .extracting("input1")
                .isEqualTo(input1);

        assertThat(connectorRequest)
                .extracting("input2")
                .isEqualTo(input2);
    }

    @Test
    void shouldFailWhenValidateNoInput1() {
        ConcatenationConnectorRequest input = new ConcatenationConnectorRequest(null, input2);

        OutboundConnectorContext context = OutboundConnectorContextBuilder.create().variables(input).build();

        assertThatThrownBy(() -> context.bindVariables(ConcatenationConnectorRequest.class))
                .isInstanceOf(ConnectorInputException.class)
                .hasMessageContaining("input1");
    }

    @Test
    void shouldFailWhenValidateNoInput2() {
        ConcatenationConnectorRequest input = new ConcatenationConnectorRequest(input1, null);

        OutboundConnectorContext context = OutboundConnectorContextBuilder.create().variables(input).build();

        assertThatThrownBy(() -> context.bindVariables(ConcatenationConnectorRequest.class))
                .isInstanceOf(ConnectorInputException.class)
                .hasMessageContaining("input2");
    }
}