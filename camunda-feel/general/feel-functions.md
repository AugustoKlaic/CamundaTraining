# Functions

### Invocation
- Invoke built-in functions or user-defined functions by its name
- Arguments can be passed positional or named
  - Positional: only the values in the order defined in the function ``contains("me@camunda.com", ".com")``
  - Named: The values with arguments name as prefix, in any order ``contains(string: "me@camunda.com", match: ".de")``
- Invocation returns null if no function exists with given name, or the arguments does not match the function signature

### User-defined
- Syntax: ``function(a,b) e``
- Defines a function with a list of arguments names and an expression (function body)
- When invoked the function assigns the values to the arguments and evaluates expression
- Functions can be defined and invoked in a context's body
- ``{ age: function(birthday) (today() - birthday).years }``