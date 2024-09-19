# Decision Table

- Represents a decision logic which can be depicted as a table
- Consists of inputs, outputs and rules
- Has name and id
- The id is the technical identifier of the decision
- Each decision must have a unique id

#### Input

- A decision table can have one or more inputs
- An input defines the id, label, expression and type of a decision table input 
- Input has a unique required id as well
- An input has a label, that is a sort description of the input
- Labels are not required, but recommended
- An Input has an expression, that defines how the value of the input clause is generated
- Expressions are simple and reference a variable available in the evaluation
- Expressions are written in FEEL
- The input has a type
- After evaluation, the decision table checks if the input can be converted to the specified type
- The type must be one of the supported ones
- Type is not required, but recommended because it prevents possible unexpected errors when trying to convert a value

#### Output

- A decision table can have one or more outputs
- An output defines the id, label, name and type of a decision table output
- Output id is an unique identifier, it is required
- Output has a label, that is a short description
- Label is not required but recommended
- Output has a name, that is used to reference the value
- If decision table have more then one output, every output must have unique names
- If only one output it is recommended to use the output id
- Output has a type, after evaluation it will try to convert the value to the specified one
- Must be one of the supported ones
- Not required but recommended, because provide type safety of the outputs values

#### Rule

- A decision table can have one or more rules
- Each rule contains input and output entries
- The inputs are conditions and the outputs are conclusions of the rule
- If each input entry satisfies then the rule is satisfied and the decision result contains the output entries
- A rule can have one or more input entries, each input entry contains an expression text in FEEL
- The input is satisfied when the expression is evaluated to true
- Empty input entry is irrelevant and is always evaluated to true, in FEEL an empty rule is ``-``
- A rule can have one or more output entries, which are conclusions of the rule
- Each rule output entry contains an expression text in FEEL
- A rule can have annotations, but it is not required. It adds more context and information to the inputs and outputs

#### Hit Policy

- A decision table has one hit policy
- Hit policies defines specifies what results of the evaluation of a decision table consist of
- If no hit policy is defined, the decision table uses the default one, that is the ``UNIQUE``
- The following hit policies are supported:
  - UNIQUE -> one rule result returned
  - ANY -> one rule result returned
  - FIRST -> one rule result returned
  - RULE ORDER -> multiple rule result returned
  - COLLECT-> multiple rule result returned
- The hit policies specifies how many rules of a decision table can be satisfied and which of the satisfied rules are included in the result
- Unique:
  - Only a single is satisfied at a time or no rule at all
  - If more than one rule is satisfied, the unique policy is violated
- Any:
  - Multiple rules can be satisfied
  - All the satisfied rules must generate the same output
  - The result contains only the output of one of the satisfied rules
  - If multiple rules are satisfied which generates different outputs, the any hit policy is violated
- First:
  - Multiple rules can be satisfied
  - The output contains only the result of the first satisfied rule
- Rule order:
  - Multiple rules can be satisfied
  - The result contains the output of all satisfied rules in the order of the rules in the decision table
  - Indicates priority
- Collect:
  - Multiple rules can be satisfied
  - The result contains the output of all satisfied rules in an arbitrary order as a list
  - No ordering in the result list
  - Aggregator can be applied to this hit policy
  - If an aggregator is added, the decision table result will only contain a single output
  - The aggregator will generate the result based on the outputs of the rules
- Aggregators used in **COLLECT** hit policy:
  - collect(sum) -> sums up all outputs from the satisfied rules
  - collect(min) -> return the smallest output value of all satisfied rules
  - collect(max) -> return the largest output value of all satisfied rules
  - collect(count) -> return the count of satisfied rules


  