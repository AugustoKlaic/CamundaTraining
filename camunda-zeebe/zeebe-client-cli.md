# Zeebe CLI

- Describe the entire process of a zeebe CLI

#### Deploy process

- Use the command DeployResource RPC: ``zbctl deploy ApplicationProcess.bpmn``
- It can receive a list of .bpmn files, to deploy more than one 
- It is an atomic operation, or all process are deployed or none of them are
- Deploys a process definition to zeebe
- If the deployment was successful zeebe will set the version automatically and the process will be visible on Operate
- The process will not have any process instances
- Zeebe will only change the version if the process has changed in some way. (any change in .bpmn file, name, diagram disposal...)


#### Starting and Cancelling

- Use the command CreateProcessInstance RPC: ``zbctl create instance ApplicationProcess``
- This command creates and starts an instance from a specific Process Definition
- Instead of the key of the process the zeebe process ID can be used
- The latest version of the process is used by default
- If the process starting was ok, a process instance should be visible in Operate
- This command above do not supply any process instance's variables
- To add the variables we need to cancel and start again with the correct command
- Use the command CancelProcessInstance RPC: ``zbctl cancel instance [processInstanceKey]``
- This command cancels the execution of an EXISTING process instance
- The process instance key used in the cancel command is returned in the start command
- The process instance key can also be found in the Operate
- Use the same command CreateProcessInstance RPC: ``zbctl create instance ApplicationProcess --variables "{json}" ``
- Now was added the flag --variables to the start command, use a json like string to specify any variable wanted
- A flag --version can be used to specify the version of the process definition is wanted the started

#### Activating job

- Use the command ActivateJobs RPC: ``zbctl activate jobs ApplicationProcess``
- Activating a job means making it available to a zeebe client for processing (complete or fail)
- An activated job means that the job has been assigned to a specific zeebe client
- If the above command returns an empty array, means that are no jobs available to be activated

#### Complete job

- Use the command CompleteJob RPC: ``zbctl complete job [jobKey] --variables "{json}"``
- This command completes a job with the payload and allows for completion of the associated task
- Completing a job tells zeebe that the execution of process can continue

#### Fail job

- Use command FailJob RPC: ``zbctl fail job [jobKey] --retries 0 --errorMessage "error external service"``
- This command will fail the job, if no retries available it will generate an incident
- Fail a job tells zeebe that the task could not be completed and needs further processing
- If an incident was risen, it can be resolved by the command ResolveIncident RPC: ``zbctl resolve incident [incidentId]``

#### Create worker

- Use the command ``zbctl create worker ApplicationProcess --handler "echo {json}"``
- Create worker combines the **complete**, **fail** and **activate** commands 
- This command will complete the service task
- This command creates a polling job worker, calling the provided handler for every job
- The handler will receive the json variables, if it completes successfully the job will complete, otherwise it will fail
- To create workers for user tasks use command: ``zbctl create worker io.camunda.zeebe:userTask --handler "echo {json}"``
- This command creates a worker that subscribes to a user task type of camunda
- This command will complete the job and complete the user task, so the process goes on

#### Publish message

- Use command PublishMessage RPC: ``zbctl publish message "Message" --correlationKey "Application-001"``
- This command publishes a message that the application will consume
- Used when catching message events are necessary