# Zeebe

- Is the process automation engine powering camunda 8
- Written in JAVA
- With zeebe:
  - define bpmn process graphically
  - implements workers with any gRPC programming language
  - build processes that react with message brokers
  - use as SaaS, or docker or self-managed
  - scale horizontally, high throughput
  - fault tolerant
- In camunda 8 the **Clients** are the main component that you will work
- The other 3 components are pre-configured but not accessible

### Architecture
- There are 4 main components in zeebe
  - Clients
  - Gateways
  - Brokers
  - Exporters

#### Clients
- Clients send commands to zeebe
- Those commands are:
  - Deploy processes
  - Carry business logic such as: start process instances, publish messages, activate or complete or fail jobs
  - Handle operational issues such as: update process instance variables and resolve incidents
- Can be scaled up and down separately from zeebe
- Are libraries embedded in an application that connects to zeebe
- Connects to zeebe via REST and gRPC
- Only JAVA and Golang clients supported officially
- A [job-worker](https://github.com/AugustoKlaic/CamundaTraining/blob/master/camunda-job-workers/job-workers.md) is a zeebe
client that uses the client API to first activate jobs, and after, complete of fail the job

#### Gateways
- Server as a single entrypoint to a zeebe cluster
- Forward requests to brokers
- Stateless and sessionless
- Can be added for load balancing and high availability

#### Brokers
- Is the distributed workflow engine that tracks the state of active process instances
- Zeebe deployment often consists of more than one broker
- Can be partitioned for horizontal scalability and replicated for fault tolerance
- Do not execute any business logic
- Responsibilities:
  - processing commands sent by the clients
  - storing and managing the state of active process instances
  - assigning jobs to workers
- Form a peer-to-peer network (no single point of failure)
  - If a broker is down the peer network will transparently substitute the worker
  - Every broker knows the next broker to pass the jobs

#### Exporters
- Provide an event stream of state changes within zeebe
- Used to logs, monitoring, history analyses, auditing, tracking incidents of process instances
- Elasticsearch is often used and are included in zeebe by default

Zeeb API
Arquitetura zeebe (brokers, partitions and clusterização)