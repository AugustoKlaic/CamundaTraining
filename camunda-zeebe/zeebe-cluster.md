# Clustering

- Zeebe operates as a cluster of brokers, forming a peer-to-peer network
- All brokers in a peer-to-peer network has the same responsibilities
- There is no single point of failure

#### Gossip membership protocol

- This protocol are implemented by zeebe, letting zeebe knows which brokers are part of the cluster
- The cluster is initialized using a set of well-known bootstrap brokers, to which others can connect
- Each broker must have at least one boostrap broker as it initial contact point
- Brokers fetches the topology from the initial one

#### Raft consensus and replication protocol (partitions)

- The fault tolerance exists usgin the ``raft protocol``, replicating data across servers
- Data is divided into ***PARTITIONS (shards)***
- Each partition has a number of replicas
- Among the replica set, a leader node is elected using ``raft protocol``
- The other nodes are passive listeners
- When the leader node is busy or unavailable, the listeners elect a new leader node
- Each broker in the cluster may be both **leader** and **follower** at same time for different partitions
- Client traffic is distributed evenly across all brokers
- Every leader broker knows it followers brokers

#### Commit

- Before a new record on a partition can be processed, it is replicated to quorum (majority) of brokers
- Ensures a record is durable, even in complete data loss of an individual broker
- Semantics of the commit are defined by the raft protocol