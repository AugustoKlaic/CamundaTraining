# Tasks

- Is a work item in the process with a specific type

#### Service tasks
- Has to have a ``taskDefinition``.
  - Is used to specify which workers can handle the service task work
- Task definition has 2 two properties:
  - Type (required): reference to specify the worker can process the job. Is like a name for the task. Has to be a string value.
  - Retries (optional): number of times a job is retryable, until failure. Default is 3x
- Task headers:
  - A task can have an arbitrary number of headers
  - Static metadata
  - Used as configuration parameters
- All job variables merge into the process instance

#### User tasks
- Model a work ta needs to be done by a human actor
- When a process instance reaches a user task, it generates a new user task instance
- This new instance will wait until the user task is completed
- User tasks are assigned to a user of the system in the tasklist
- user tasks has attributes to this assignment:
  - ``assigne`` - associate the task to a specific user
  - ``candidateUsers`` - specify the users that the task can be assigned  to
  - ``candidateGroups`` - specifies a group of users that the task can be assigned to
- Can be scheduled, defining when a user can interact with the task
- to schedule use: ``dueDate`` or ``followupdate``
- All zeebe user task variables are merged into process instance
- Can accept forms to facilitate user experience when completing tasks in tasklist

#### Receive tasks
- References a message
- Wait until a proper message is received
- When an instance arrives at a receive message task, a corresponding message subscription is created
- Waits until message arrives
- For a task to receive a message it must have the same name of message and the correlation key expression

#### Business rule task
- Is used to model the evaluation of a business rule, like in decision tables (DMN)
- The DMN model will evaluate the process instance and if is successful the flow continues but if not an incidents is raised
- Links a task of the BPMN with a decision model notation (DMN) to evaluate variables

#### Script tasks
- Script task are used to run scripts
- When a token arrives in a script task, the integrated FEEL scala engine evaluates the script task
- To define the script task, use the ``script`` extension element
  - Define the FEEL expression inside the expression attribute
  - Define a name of the process variable in the resultVariable to be the output

#### Send tasks
- Used to model a publication of a message to an external system
- Kafka topic or mail service
- Same as a service task, implemented by a job worker, the only difference is visual between the two, because of the symbol in the task
- Are not processed by zeebe itself, but by the job worker

#### Manual tasks
- Define a task that is external to the BPMN engine
- Used to model work that is done by somebody that the engine does not need to know
- Tasks that have no know system or UI interface
- No benefit in process automation

#### Undefined tasks
- Aldo know as abstract task
- Define a task that the work type is unspecified
- Use to model a process that is not automated
- Or used while the process automation is still in development
- Handled as a pass-through activity