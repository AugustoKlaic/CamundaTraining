# Connector SDK

- Allow development of custom connector with JAVA
- Easy and locally tested
- Abstract camunda internals that are present in ``Job workers``
- Provides API for:
  - Fetching and deserializing input data
  - Validating input data
  - Replacing secrets in input data

### Outbound project overview
- On [this repository](https://github.com/AugustoKlaic/CamundaTraining/tree/master/camunda-outbound-connectors) you can see the
  structure of the project that I will talk here

- This dependency is needed:
  - ``implementation "io.camunda.connector:connector-core:${version.connectors}"``
- On folder ``element-templates`` is the json template of the connector exposing its configurations, properties, name and other features
- On ``source/main/java/camundaoutboundconnectors`` is all the source code with the logic, consisting in:
  - One implementation of ***OutboundConnectorFunction*** -> ``ConcatenationConnectorFunction.java``
  - At least one input data object -> ``ConcatenationConnectorRequest.java``
  - At least one result object -> ``ConcatenationConnectorResult.java``
- On ``/resources`` is needed to add the ``META-INF`` folder with a file that contains your function class name to be exposed
- #### Template json file important things:
  - name -> Used to bind the code with BPMN
  - appliesTo -> Used to determine which element of BPMN the connector will be applied
  - properties -> all input fields, result fields, bindings with zeebe context
    - This json below is important, it is inside of properties list, but it shows which version of the process it is using, 
    the field ``value`` the last number indicates that
    ```
      {
        "type": "Hidden",
        "value": "io.camunda:concatenation-api:1",
        "binding": {
          "type": "zeebe:taskDefinition",
          "property": "type"
        }
      }
    ```
  - Can map errors into BPMN errors

- #### Runtime logic
  - Class needs to implement the ***OutboundConnectorFunction*** interface
  - Connector runtime environments will call this function
  - Handles input data, execute business logic, and can return a result
  - The OutboundConnectorFunction consist of one method, called ``execute()``
  - Receives all data necessary to process by the ***OutboundConnectorContext***
    - This deserializes the input data
    - Execute the connector's business logic
  - Connector throws a ***ConnectorRetryException*** by default
  - You can customize this retry exception to:
    - Increase or decrease retry times
    - Increase or decrease backoff duration
  - The annotation ``@OutboundConnector`` provide default meta-data, and auto-discover runtime behaviour

- #### Input data
  - Data comes from the process instance that executes the connector
  - The connector can retrieve data as raw JSON or deserialize it into our own object
  - Can be primitive types o JAVA or custom objects created by you
  - Method bindVariables:
    - Automatic mapping to JAVA object
    - Attempt to replace connector Secrets
    - Translates the JSON string to an JAVA object
  - Method getVariables:
    - Old school way, get a map key and cast it to your JAVA type

### Inbound project overview
- On [this repository](https://github.com/AugustoKlaic/CamundaTraining/tree/master/camunda-inbound-connectors) you can see the
  structure of the project that I will talk here
- This dependency is needed:
  - ``implementation "io.camunda.connector:connector-core:${version.connectors}"``
- On folder ``element-templates`` is the json template of the connector exposing its configurations, properties, name and other features
- On ``source/main/java/camundainboundconnectors`` is all the source code with the logic, consisting in:
  - One implementation of ***InboundConnectorExecutable*** -> ``MyConnectorExecutable.java``
  - At least one input data object -> ``MyConnectorEvent.java``
  - One connector properties -> ``MyConnectorProperties.java``
- On ``/resources`` is needed to add the ``META-INF`` folder with a file that contains your function class name to be exposed
- The JSON template follows the same logic and have the same components and attributes 
- #### Runtime logic
  - Need to implement ***InboundConnectorExecutable*** interface
  - Connector runtime environments will call this function
  - Handles input data, execute business logic
  - Consists of two methods: ``activate`` and ``deactivate``
  - Activate method is a trigger, to start listening to inbound events
  - Activate has to be asynchronous
  - Deactivate method is just a graceful shutdown hook
  - Deactivate must release all resources used by the subscription
  - Can use Jakarta bean validation on properties line @NotEmpty...
  - Has multiple types of validation, that can be used or customized as your needs


### Testing
 - No need to test camunda internals or provide mocks
 - Focus on business logic: Data validation, secret replacement, core logic of connector
 - Uses ***OutboundConnectorContextBuilder***  to build all context of camunda

### Runtime environments
- There are 3 runtime envs available: Spring, Docker and Custom(self-managed)
- On docker basically create a docker image following the camunda documentation
- For Spring some dependencies are needed
- Here in [this project](https://github.com/AugustoKlaic/CamundaTraining/tree/master/spring-camunda-microservice-orchestration) 
there are 2 ways of running environments, the SpringBoot way and the sel-managed way