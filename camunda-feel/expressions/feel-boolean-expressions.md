# Booleans

## Expressions
    
Boolean expressions creates a new boolean value.

### Literal
- Literal expression are just the boolean values
- ``TRUE`` or ``FALSE``

### Comparison
- Compares two values with one of the following operators
- ``=`` or ``!=`` or ``<`` or ``<=`` or ``>`` or ``>=`` or ``between [x] and [y]``
- Both values must be of the same type, otherwise the result is **null**
- The equals operator has only one equals operator

### Null check
- Any value or variable can be compared with null to check if it is equal to null or if it exists
- Comparing null to a value different fromm null results in false
- A context entry compared to null  is true if the **value** of the context entry is null or if the context entry does not
contain an entry with this key

### Conjunction/AND
- Combines multiple boolean values following the ternary logic
- Result is true if all the values are true
- Result is false if one value is false
- If a value is not a boolean the result is null

### Disjunction/OR
- Combines multiple boolean values following the ternary logic
- Result is true if at least one value is true
- Result is false if all values are false
- If a value is not a boolean the result is null

### Instance of
- Check if the value is of the given type
- Use the type Any to check if the value is not null.

### Unary-tests/IN
- Evaluates unary-tests with the given value
- The keyword **IN** separates the value from the unary-tests 