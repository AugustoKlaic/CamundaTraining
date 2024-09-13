# BPMN

- Is a graphical notation to represent complex processes
- Is an XML document that has visual representation

### Elements

#### Sequence flow
- Define order in steps that the process will happen
- Is an arrow connecting two elements
- The direction of the arrow indicates the order of execution
- The process instance follow the arrows in a process
- Zeebe is responsible for drive this instance through the process

#### Tasks
- Atomic units of work
- Create a meaningful result when finalized
- When token reaches a task zeebe creates a job and notify a worker to perform work
- When the complete command is received, the token proceeds to next the next task on the sequence flow
- One task can represent one microservice invocation
- The granularity of a task is up to the person developing it


#### Gateways
- Route tokens through the process
- Exclusive gateway:
  - Choose only **ONE** sequence flow out of many to follow based on data
- Parallel gateway:
  - Generate new tokens for each sequence flow

#### Events
- Represents a thing that happens
- A process can react to events or emit events
- Can be added to the process in various ways
  - Interrupting token progress
  - Making the token wait at certain point

#### Subprocess
- Are element containers
- Define common functionality
- 