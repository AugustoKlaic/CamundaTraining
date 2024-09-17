# Events

- Represent things that happen
- A process can be reacting to event or emitting event
- Can make a token wait at certain point or interrupt the token progress
- Can be thrown (emitted) or caught (reacted)
- And it is separated in **start**, **intermediate** and **end** events
- Start events are always catching(react to something)
- End events are always throwing(emit something)
- Intermediate events can be both types, indicating that something happened (throwing) or wait to react to some event (catching)

#### Boundary events

- Model something that should happen if an event occur while an activity is active
- Boundary events must be **intermediate catch events** 
- Can be **interrupting** or **non-interrupting**
  - Interrupting event: once triggered the activity where the event is attached to is terminated
  - Non-interrupting event: do not terminate the original flow, occur in parallel
- Message boundary events attached to task must have unique message names
- Signal boundary events attached to task must have unique signal names
- Time boundary events can be interrupting and non-interrupting:
  - Interrupting timer events must have a time duration or a time date
  - Interrupting timer events cancel the original activity
  - Interrupting timer events are used for timeouts
  - Non-Interrupting timer events must have either a time duration, time cycle definition or a time date definition
  - Non-Interrupting timer events used to model notifications
  - 

#### None events

- No specified events, or blank events
- There are 3 types of none events: **Start**, **End** and **Intermediate**
- Start none events: 
  - is where a process instance or a subprocess starts when it is activated
  - a process can have only one none start event, besides other start events type
  - A none start event is required if you want to trigger a process via form
- End none events:
  - a process can have multiple none end events
  - when a none end event is entered the current execution path is ended
  - if an activity has no outgoing sequence flow, it behaves same as if it would be connected to a none end event
- Intermediate none events:
  - Used to indicate some state achieved by the process
  - Useful to monitor and understand how the process is doing
  - Engine does not do anything in the event, just passes through it

#### Message events

- Events that references a message
- Used to wait for a proper message to arrive
- There are three types of message events: **Start**, **End or Throwing** and **Intermediate**
- Start message events:
  - A process can have one or more message start events but everyone has to have a unique message name
  - Messages subscriptions are created for each start event, the ones from a previous version are closed
  - Messages are not correlated to start event if the message was sent before the process deploy or a new version is available
  - In a message start event, if a process instance is up and running that was started by the same message correlation key, a new process is not created
  - The message awaits in a buffer and when the previous is finalized the one waiting is started
  - If the correlation key is empty, it starts a new process instance without checking
- Intermediate message events:
  - A subscription message catch event is created
  - The process instance stops and wait until message is correlated
  - An alternative to intermediate message catch events is a receive task, which behaves the same but can be used together with boundary events.
- End/Throw message events:
  - A process can have N intermediate message throw events or message end events
  - Model a publication of a message to an external system
  - Works same as service tasks and send tasks, have the same job-related properties
  - The difference between message throw events and a task is just the visual representation in the BPMN
  - When process instance enters a throw message event it creates a job, after completion, the process continues or finishes in case of end event

#### Signal events

- Reference a signal
- Broadcast signal, trigger all events of the type that was broadcast
- There are three types of message events: **Start**, **End or Throwing** and **Intermediate**
- Start signal event:
  - Used to start process instances
  - If more than one process has the same signal start event type, when the signal is thrown all processes with the same type of the signal start an instance
  - Signal subscriptions only exist for exists for the latest version of a process definition
  - Deploying new version of a process deletes the previous signals subscriptions
- Intermediate signal catch event:
  - When entered by a process instance, a signal subscription is created
  - Process instance stops and waits for a broadcast signal with same name
  - When a signal with same name/type is broadcast the catching signal is triggered
- End/Throw signal events:
  - A process can have N intermediate signal throw events or signal end events
  - When a signal throw events is entered, it broadcasts a signal that can trigger signal subscriptions

#### Timer events

- Triggered by a defined timer
- There are two types of message events: **Start** and **Intermediate**
- Starting timer event:
  - Can have one or more timer start event in the process
  - Must have either a time date or time cycle definition
  - When process deployed, it schedules a timer for each timer start event
  - Previous versions timers are cancelled
  - When a timer is triggered, a new process instance is created
- Intermediate timer events:
  - Are catching events
  - Can be a time duration or a date
  - When an instance enters a catch timer event, a corresponding time is scheduled
  - Process instance waits until the scheduled time ends and continues the process
  - Non-Interrupting timer events can have repetitions loops
  - Non-Interrupting timer events with time duration will trigger a single time once the date is reach

#### Error events

- Allow process to react to errors within a task
- Error events reference errors thrown in the process
- Must define an error code
- Error code is to determine which catch event will catch the error
- Error events can be **thrown** or **caught**
- Throwing:
  - Throw errors are end events
  - Can be thrown from a job-worker
- Catching:
  - Can be caught by a catch event, specifically using boundary error event or an event subprocess
  - Not possible to define multiple error events with the same error code in a single scope
  - Cannot have multiple catch-all events in a single scope
- Error boundary events and error events subprocesses must be interrupting
- Unhandled errors turn into incidents
- Business reactions > technical reactions

#### Escalation events