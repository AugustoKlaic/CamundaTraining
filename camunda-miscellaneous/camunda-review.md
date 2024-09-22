# Test last review by exam blueprint

## Modeling (15% of the test)

- More detailed content in the folder [Camunda-modeler](https://github.com/AugustoKlaic/CamundaTraining/tree/master/camunda-modeler)

### Pool and Lanes

- Pools are a process orchestrator
- Separate processes that are part of each other but cannot be modeled together
- Departmentalization of a process
- Communicate with others process using message flows
- Separate the process based on its actors
- Has process control, assign the tasks

- Lanes shows who are responsible for the tasks of the process
- Used to assign roles, departments, applications....

### Tasks

- Tasks are a unit of work
- Is something that has to be done
- Can represent different types of work (automated service, manual work, message receiving or sending...)
- Always return a result of some operation

### Gateways

- Control the flow of execution of the process
- There are 4 types of gateways
  - Parallel
  - Exclusive
  - Inclusive
  - Event based
- Every gateway does something different
- Parallel gateway will execute all outgoing flows in parallel
- Exclusive gateway will always execute one unique flow outgoing the gateway
- Inclusive gateway will execute 1 or many outgoing flows
- Event based gateway will execute the outgoing flow based on an event received

### Events

- Events are something that happens in a bpmn process
- A process react to an event (catching it or throwing it)
- Events can interrupt the flow of execution
- Can be attached to a task to add new options flows of executions (boundary events)

### Subprocesses

- A subdivision of a process
- It can be embedded on original process or apart from the original process (call activity)
- It groups some tasks that together feature a functionality that can be reused or have different objective compared with the parent process
- Encapsulate complexity
- Can have attached events

### Multi instances

- Mark a task or subprocess as multi instance
- Execute the same task or subprocess for each instance in the collection
- Camunda received a collection localized in the body of the process instance
- Camunda only continues with the process flow after finalizing the processing of all elements in the collection
- Can be done in parallel or sequential
