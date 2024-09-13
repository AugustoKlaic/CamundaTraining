# Data flow

- Every BPMN process can have one or more variables
- Variables are a key-value-pair and holds contextual data
  - Contextual data is used by job workers or by the sequence flow gateways
- Provided when an instance is created, a job is completed, or message is correlated
- Job workers get all variables of a process instance
- When the worker finishes the job it sends back the variables and merge the ones that it changed

#### Variable scopes vs. token-based data
- A process can have concurrent paths
- Variables are part of the process and not part of the token
- Concurrent paths generates more than one token, but those tokens access the same variables adding and modifying the values
- To avoid problems take care with variables scopes
- Use updated variables, output variable mapping or embedded subprocess can help isolate the variables scope