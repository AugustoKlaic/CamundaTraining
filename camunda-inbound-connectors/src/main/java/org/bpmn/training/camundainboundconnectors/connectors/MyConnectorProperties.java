package org.bpmn.training.camundainboundconnectors.connectors;

public class MyConnectorProperties {

    private String eventToMonitor;
    private String directory;
    private String pollingInterval;

    public MyConnectorProperties(String eventToMonitor, String directory, String pollingInterval) {
        this.eventToMonitor = eventToMonitor;
        this.directory = directory;
        this.pollingInterval = pollingInterval;
    }

    public MyConnectorProperties() {
    }

    public String getEventToMonitor() {
        return eventToMonitor;
    }

    public String getDirectory() {
        return directory;
    }

    public String getPollingInterval() {
        return pollingInterval;
    }
}
