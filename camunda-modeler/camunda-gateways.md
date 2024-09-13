# Gateways

- Gateways are elements that route tokens in more complex patterns than plain sequence flow
- The condition of the gateway has to be a boolean value or a FEEL expression that returns a boolean value

#### Exclusive gateway
- Allow one decision based on data
- Exclusive gateway with multiple outgoing flows, all flows must have a ``conditionExpression`` except one, the one that does not have is the ``default flow``
- The token take the first sequence flow that evaluates to true
- If no conditions fulfilled, the token takes the default flow
- If no default flow is defined and no conditions are fulfilled, an incident is created
- Also used to join multiple incoming flows
- Does not merge the incoming concurrent flows like a parallel gateway

#### Parallel gateway
- Allow the split of the flow into concurrent paths
- When a parallel gateway is entered all outgoing sequences flows are taken
- The paths are executed concurrently and independently
- The concurrent path can be joined using the same parallel gateway with multiple income sequence flows
- The instance waits at the parallel gateway until each incoming sequence flow is taken

#### Event-based gateway
- Allow decisions based on events
- Must have at least two outgoing sequence flows
- Each sequence flow must be connected to an intermediate catch event of type: ``timer``, ``message`` or ``signal``
- When the token reaches the gateway, it waits at the gate until one of the events is triggered
- When the first event is triggered the outgoing sequence flow of the event triggered is taken
- No other events of the gateway can be triggered afterward

#### Inclusive gateway
- Allow multiple decisions based on data
- If inclusive gateway has multiple outgoing flows, all flows must have a condition to define it is taken
- If inclusive gateway has only one outgoing flow, then it does not need to have a condition
- Optionally, one of the sequence flows can be marked as ``default``, this sequence flow **SHOULD NOT** have a condition
because its behaviour depends on the others conditions
- The process takes all sequence flows where the condition is fulfilled, if no condition fulfilled then it takes the default flow
- If no condition is fulfilled and there is no default flow, an incident is created
