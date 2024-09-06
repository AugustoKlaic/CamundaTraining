# Connectors

- Connectors are reusable
- Are often represented as tasks in BPMN diagram
- Consists of two parts:
  - The programming code in Java to connect to the external system
  - The user interface to be used during modeling
    - Users modeling the bpmn will just know the configuration options exposed by the connector
- Connectors OOTB are pre-built connectors to popular external system, like AWS, Github...
  - They are still customizable

### Types of connectors

- Connectors are categorized by the direction data flows into or out of Camunda 8.

#### Outbound
- Allow workflows to trigger external systems or services, allowing integration with other business or architecture  
- The connector outbound code is executed when workflow reaches the task
- Use it when something needs to happen in other processes
- Ex: REST call, publishing message

#### Inbound
- Enable workflows to receive data or messages from external system
- Can create new process instance
- Java inbound connector code lifecycle is for long-running operations like:
  - Listening for messages
  - Waiting webhook
- Is activated when it detects an element
- Is deactivated when update or delete process definition
- Are linked to process definitions and not process instances
- Is reusable for all instances in the process
- Needs Camunda Operate API to work
- Use when something needs to happen within the process
- There are 3 types of inbound connectors:
  - WebHook -> creates a webhook to a camunda workflow
  - Subscription -> subscribes to a message queue
  - Polling -> periodically pools external system (HTTP pooling)

#### Protocol
- Serves as either an inbound or outbound connector
- Highly generic