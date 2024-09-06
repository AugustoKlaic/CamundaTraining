# Modeling Connectors

### Inbound

- They are modeled as **catch events**
- When BPMN is deployed, the connector becomes ready to receive requests
- They can be start events, intermediate catch events and boundary events

### Outbound

- They are modeled as **tasks**
- Uses FEEL to manipulate data or properties from process
- By default, the connector tries 3 times to retry a failed execution
- By default, there are no delay between retries
- Retries can be set to more or to fewer times
- Delay between retries also can be customized, using property ``retryBackoff ``

### Connector Template

- Is a JSON file with the description of fields, properties, configurations for the connector
- To reference a connector in JAVA code use the property ``name`` 