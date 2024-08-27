package org.bpmn.training.camundainboundconnectors.connectors;

public class MyConnectorEvent {

    private WatchServiceSubscriptionEvent event;

    public MyConnectorEvent(WatchServiceSubscriptionEvent event) {
        this.event = event;
    }

    public MyConnectorEvent() {
    }

    public WatchServiceSubscriptionEvent getEvent() {
        return event;
    }
}
