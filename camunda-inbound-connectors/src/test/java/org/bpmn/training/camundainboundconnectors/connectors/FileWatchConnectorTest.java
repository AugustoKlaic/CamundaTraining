package org.bpmn.training.camundainboundconnectors.connectors;

import io.camunda.connector.api.inbound.InboundConnectorContext;
import io.camunda.connector.test.inbound.InboundConnectorContextBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileWatchConnectorTest {

    private String eventToMonitor;
    private String directory;
    private String pollingInterval;

    @BeforeEach
    void setup() {
        this.eventToMonitor = "ENTRY_CREATE";
        this.directory = "C:\\Users\\gutos\\Desktop";
        this.pollingInterval = "30";
    }

    @Test
    void shouldExecuteFileWatchConnector() {
        MyConnectorProperties input = new MyConnectorProperties(eventToMonitor, directory, pollingInterval);
        InboundConnectorContext context = InboundConnectorContextBuilder.create().properties(input).build();

        MyConnectorProperties connectorInput = context.bindProperties(MyConnectorProperties.class);

        assertThat(connectorInput)
                .isInstanceOf(MyConnectorProperties.class)
                .extracting("eventToMonitor")
                .isEqualTo("ENTRY_CREATE");

        assertThat(connectorInput)
                .isInstanceOf(MyConnectorProperties.class)
                .extracting("directory")
                .isEqualTo("C:\\Users\\gutos\\Desktop");

        assertThat(connectorInput)
                .isInstanceOf(MyConnectorProperties.class)
                .extracting("pollingInterval")
                .isEqualTo("30");
    }
}
