# Partitions

- In zeebe all data is organized in partitions
- Partition is a persistent stream of process-related events
- Partitions are distributed among the brokers
- Partitions numbers can be configured according to the needs of the application
- When a process is deployed, it is deployed to the **first partition**
  - Then the process is distributed to all partitions, receiving the same key and version
- When process instance is started, the client routes the requests to the partition where the instance was published,
all the subsequent processing of that instance happens in that partition

#### Distribution over partitions
- When process instance is created in a partition, its state is stored and managed by the same partition, until termination
- Partition distribution:
  - When client sends a command, the gateway chooses partition in round-robin manner and send the requests and process instance creation to the chosen partition
  - When client publishes a message to trigger a message start event, the message is forwarded to the partition that 
has the same ``correlation-key``, after published the process instance is created on that partition 
  - When timer start event, the process instance is always created on partition 1

#### Scalability
- Partitions are used to scale the process processing
- Partitions are dynamically distributed in a zeebe cluster
- For each partition there is a leading broker at a time
- The leader accepts requests and performs

#### Partition data layout

- Partition is a persistent append-only event stream
- Initially a partition is empty
- The data-structure is like FIFO, first in first out
- Each entry(requests and instances) has a position in the partition which uniquely identify it

#### Replication
- Fault-tolerant, data is replicated from the leader broker of the partition to its followers
- Followers are other brokers that maintain a copy of the partition without doing any processing
- Odd replication factor better than even replication factor
  - replication factor of 4 are equal than a replication factor of 3 and is worse than a replication factor of 5

#### Partition distribution
- The default distribution is done by round-robin fashion across all brokers