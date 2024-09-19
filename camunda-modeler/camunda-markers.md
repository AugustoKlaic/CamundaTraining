# Markers

#### Multi-instance

- An activity marked with multi-instance ``|||`` executes multiple times, once for each element in collection
- Following activities can be marked as multi-instance: Service and Receive tasks, embedded subprocess and call activities
- ``|||`` = parallel and 3 horizontal lines = sequential
- Parallel is the default execution for multi-instance
- Sequential executes one by one, parallel executes all at same time
- Must have an ``inputCollection`` expression, that defines the collection to iterate over
- To access the actual element of the interaction is used a variable called ``inputElement`` and is stored as a local variable
- Output is defined in two variables ``outputCollection`` and ``outputElement``
- OutputCollection variable collects the entire iteration generated objected, saved locally and in the end are propagated to parent scope
- OutputElement variable defines the output of the instance, is the actual element of the iteration to be output, not propagated to parent process
- Boundary events can be attached to multi-instance activity
- When interrupting event is triggered it terminates all active instances and the multi-instance body of execution, variables are not propagated
- Non-interrupting event do not interfere in the multi-instance processing
- Has a variable called ``loopCounter``, it starts at 1
- Completion condition expression can be used to terminate the process earlier, if the expression evaluates to null it will end the multi instance process and terminate all active instances

#### Compensation

- Associated with the compensation boundary event
- The mark is placed on the activity that comes right after the outgoing flow of a compensation boundary event
- Are called compensation handlers
- A subprocess can be used as activity to a compensation event, and is marked with the compensation symbol
- Call activities can also be an compensation event, and also is marked with the symbol. Do not trigger the compensation event of the child process
- In multi-instance activity the compensation handler activity must have the multi-instance symbol too, to be called to every element of the list
- If not marked with multi-instance, the handler will execute only one time
- Compensations can be interrupted