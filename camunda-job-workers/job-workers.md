# Job workers

- Is a service capable of performing a particular task in a process
- Each service task of a diagram, that is implemented as a JobWorker, generates a specific worker
- Each time the task has to be performed, this is represented as a **JOB**
- Zeebe is an "at least once" system
- Worker must be idempotent (worker code will be executed more than once for the same job)
- A job has 4 properties:
  - Type 
    - Describes the work item
    - Is defined in each task in the process
    - Referenced by workers to request the jobs they are able to perform
  - Custom headers:
    - Additional static metadata
    - Used to configure reusable job workers
  - Key:
    - Unique key to identify a job
    - Used to handle in the results of a job execution
    - Used to report failures of a job execution
  - Variables:
    - The contextual/business data of the process instance required by the worker
- Job workers can be used in streaming fashion
  - Jobs are automatically activated and pushed downstream to workers, not requiring polling

### Requesting Jobs

- Job requesting are done on a regular interval (polling) - can be configured on zeebe client
- Zeebe streams orchestrate the sending of jobs to the workers, based on type
- After receiving the job, the worker will process and then send a ``complete`` or ``fail`` command for the job
- To scale up zeebe can have many workers of the same type
- Zeebe ensures that each job is sent to only one worker
- To request jobs is used the **Long polling** strategy

### Job queueing
- Zeebe decouples creation of jobs from performing work on them
- Can create as many jobs as needed regardless if there is a worker available
- Zeebe queues jobs until worker request them
- Zeebe is highly available so the workers don't need to
- When workers get back up normal again they will follow the zeebe queue of jobs


### Completing or failing jobs
- The worker informs zeebe if the job is ``completed`` or ``failed``
- When worker complete the job, it sends a ``complete job`` command along with all variables, that are merged into the zeebe context
- When worker could not complete the job, it sends a ``fail job`` command along with the remaining retries
- When retries are greater than 0, the worker retry the job, if is lower the worker raises an incident
- When failed job, a retry back off can be set to wait more time and delay the retries


### Timeouts
- When the job is not concluded or failed within the timeout, zeebe will reassign the job to another worker
- Does not affect the retry count
- When zeebe reassign the job to another worker, it may lead to two workers working in the same job at the same time
  - If this occurs only one worker can successfully complete the job. The other ``complete job`` command is rejected with a ``NOT FOUND`` error
- Timeout can be extended or shortened via ``updateJobTimeout`` gRPC command




Anotei pra nós aqui os pontos que o pessoal falou da prova na reunião agora:

Prestar atenção no tempo da prova -> 75 min para 60 questões
Job workers (not found, time out, multi instancias)
Job workers (gateway exausted) -> nao tem no curso pra prova
FEEL (bastante coisa de detalhes na sintaxe)
Zeeb API
forms(Dynamic list, hide fields e feel)
Zero código Java, so questões teóricas. Ex: "Tenho um JobWorker X que faz Y e deu o erro Z,o que devo fazer para corrigir?"
Arquitetura zeebe (brokers, partitions and clusterização)