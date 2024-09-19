# Decision literal expression

- It's only a literal expression and a variable
- Written in FEEL
- It has a name, not required
- It has an id, that is required an unique for each expression
- The id is the technical identifier of the decision

#### Literal expression

- Specifies how the value of the decision is generated
- Can be used to do complex calculation or combine the outputs of required decisions

#### Variable

- The name of the variable is used to reference the value of the expression result
- Recommended to use the decision id as the variable name
- Has a type
- Must be one of the supported ones
- Variable will be converted to the type specified