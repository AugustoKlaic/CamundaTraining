# Miscellaneous topics

- In a BPMN process what is the best component for an external HTTP call?
  - A: REST API Outbound connector

- How to filter a second table with the value of the first table in DMN?
  - A: Have to create Decision Requirements Diagram (DRD) where the output of the first table is used on the second table
the two tables must be linked with the arrow flow of the decision

- What is the API for client creation?
  - A: Administration API, it provides a programmatic interface for managing camunda clusters and API clients. Offers endpoints
for cluster backup, creation and deletion as well as client and member management

- Optimize can receive data from external databases?
  - A: Optimize do not make direct external queries, but external data can be imported into optimize

- In a worker scenario, how to send two different errors, one should throw exception and the other should let a retry for 3x?
  - A: For the error that will retry 3x, a ``failJob`` command is needed, and for an immediate error to be thrown a ``throwError`` command is needed

- In a screen with a dynamic list that can be changed, items can be added or removed, how to show the consolidated result at the end of the form?
  - A: Create a text field with a FEEL expression that summarize the items quantity multiplied by the value. ex: ``{{sum(dynamicList[quantity*value])}}``  

- Given the following input: ``JSON { contact:{[name,phone][name, phone]}}`` what is the best way to show it on screen? Considering it can be changed.
  - A: Using a dynamic list component of camunda forms

- After creating a connector template, how to use it in a BPMN?
  - A: The connector has to be published to organization

- After some job worker lifetime passed the frequency of timeout errors increased, how to solve?
  - A: Best option is to increase the job worker threads, because the job worker accumulate a big backlog of pending jobs.
Other solutions can be, increase the timeout duration (maybe this solution will mask the real problem) 
and adjust the poll interval (if the worker is spending too much time polling jobs)

- When a job worker returns an error and still has retries, what happens?
  - A: As the worker still has a retry, it will retry the failed job, zeebe will decrease the numbers of retries and 
return the job the to queue. In the queue, the job will be picked by any worker that is available of the same type of the job
not necessarily by the same worker that picked it before.

- What is the communication between zeebe and his workers?
  - A: Zeebe creates a job and stores it in a queue, then a job worker makes a polling request to zeebe to retrieve a job 
with same type the worker can process. The worker then fetch the job information and lock the job for not let any other worker take the same job.
The worker process the job and then sends a command message to zeebe (fail or complete), if a job is failed it will retry
and then open an incident if the fail persists, if the job is completed successfully zeebe will go on with the workflow

- A terminate signal from a subprocess will cancel the execution of the subprocess and the execution of the father process?
  - A: The subprocess will be cancelled but the father process will not be affected, this is applied to embedded subprocess and call activities

- What is needed for the variables of the process appear on the subprocesses?
  - A: Nothing is needed, the subprocesses inherit the variables from the father process. But if new variables are created 
on the subprocess it will not be passed to the father process. Variables of subprocess can be mapped to father process as outputs.

- What is the best way to guarantee that a task a employee see the specified data?
  - A: User task, because it will appear on camunda tasklist and the employee will work on it

- What field is used to map the return of a connector to the business flow?
  - A: There are two reusable objects that are an output mapper of the response of the connector ``Result Variable`` and ``Result expression``.
And there is an object for mapping error responses too, that is the ``Error expression``

- What is the relation between inputs in DRD with the inputs of the DMN table?
  - A: There is no relation, the inputs mapped in the DRD are illustrative and a way of documenting and facilitating readiness,
and the inputs in the DMN table are in fact used to evaluate the FEEL expression and return a result.
