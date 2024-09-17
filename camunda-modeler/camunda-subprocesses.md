# Subprocesses

- Are element containers that allow defining common functionality

#### Embedded subprocess

- Must have only one none event start event
- Other start events are not allowed
- Subprocess stay active as long as one containing element is active
- Used with boundary events, one or more can be attached to a subprocess
- When interrupting boundary event is triggered, the subprocess is terminated

#### Collapsed subprocess

- A subprocess can be collapsed to hide its details when just the overall idea of the process is necessary
- Purely for display purposes
- Not fully supported by Optimize

#### Call activity

- Is a reusable subprocess
- Allows for call and invoke this subprocess in another process
- Similar to embedded subprocess but is externalized
- Can be invoked by different processes
- When a called it starts at the none start event, ignoring other start events
- Called by the ``processId`` field
- Always is used the latest version of the call sub process defined
- Can have interrupting and non-interrupting boundary events
- When interrupting boundary event is triggered the call activity is terminated, the variables of the call activity used are not propagated
- When non-interrupting boundary event is triggered the call activity are not affected, but the variables of the call activity do not see the variables of the outgoing flow of the boundary event

#### Event subprocess

- Is a subprocess triggered by an event
- Can be added globally or locally inside an embedded subprocess
- Must have **ONE** start event of the following types: ``timer``, ``Message`` or ``Error``
- Behaves like a boundary event, but is inside the scope instead of attached to it
- Can be interrupting (solid border) or non-interrupting (dashed border)
- Non-interrupting event subprocesses can be triggered multiple times
- Interrupting event subprocesses can only be triggered once
- When interrupting event subprocess are triggered, all active instances of the scope are terminated, included instances of other non-interrupting event subprocesses 
- If event subprocesses is triggered, its containing scope is not completed until the event scope is completed
- Unlike boundary events, an event subprocess is inside the scope of the subprocess, so it can access and modify its variables
