# Unary-tests

- Is a special type of boolean expression
- Is used as input entry of a decision table
- Returns true when:
  - The expression evaluates to true when the input value is applied to it.
  - The expression evaluates to a list, and the input value is equal to at least one of the values in that list.
  - The expression evaluates to a value, and the input value is equal to that value.
  - The expression is equal to - (a dash).

### Comparison
- Compares the input value with a given value
- Input value is passed implicitly as the first argument of the operator
- Both values must be of the same type, otherwise results in null
- Operators: ``=``, ``<``, ``>``, ``<=``, ``>=``

### Interval
- Check if the input is within a given interval between x and y
- An interval has two boundaries
  - Open interval: ``(x..y)`` or ``]x..y[``
    - Exclude the given values
  - Closed interval: ``[x..y]``
    - Includes the given value
- Input value is passed implicitly to the operator 
- ``(2..5) = input > 2 and input < 5`` or ``]2..5[ = input > 2 and input < 5`` or ``[2..5] = input >= 2 and input <= 5`` or ``(2..5] = input > 2 and input <= 5``

### Disjunction/OR
- Combines multiple unary-test expressions following the ternary logic.
- Returns true if at least one unary-test evaluates to true.
- Otherwise, return false

### Negation/NOT
- Syntax: ``not(a)``
- Negates a given unary-test expression
- The expression can be a comparison, an interval, or a disjunction
- It returns true if the given unary-test evaluates to false

### Expressions
- Evaluates an expression that returns a boolean value
- Invokes a function
- The input value can be accessed in the expression by using the symbol ?
- ``contains(?, "good")`` - check if the input value (string) contains "good"
- ``ends with(?, "@camunda.com")`` - checks if the input value (string) ends with "@camunda.com"