package org.bpmn.training.camundainboundconnectors.connectors;

public class WatchServiceSubscriptionEvent {

    private String monitoredEvent;
    private String directory;
    private String fileName;

    public WatchServiceSubscriptionEvent(String monitoredEvent, String directory, String fileName) {
        this.monitoredEvent = monitoredEvent;
        this.directory = directory;
        this.fileName = fileName;
    }

    public WatchServiceSubscriptionEvent() {
    }

    public String getMonitoredEvent() {
        return monitoredEvent;
    }

    public String getDirectory() {
        return directory;
    }

    public String getFileName() {
        return fileName;
    }
}
