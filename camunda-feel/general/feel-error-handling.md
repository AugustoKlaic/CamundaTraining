# Error handling

- FEEL doesn't define any error handling. 
- If something goes wrong, return null.

### Null-friendly
- If an expression can't be evaluated successfully it returns null
- Errors that return null: 
  - No variable exists with the given name
  - No context entry exists with the given key
  - No function exists with the given name
  - A function can't be invoked successfully with the given arguments
  - A value is compared to another value of a different type
  - An operation is not defined for the given values

### Handle null values
- Expressions and operators can deal with nulls
- In these cases they return null
- Or you can handle explicitly using a null check
- ``a.b > 10 = null`` or ``[{a: 1}, {b: 2}].a = [1, null]`` or ``[{a: 1}, {b: 2}][a < 10] = [{a: 1}]`` or ``a != null and a.b > 10`` or ``get or else(a, "prio-99")``

### Assertions
- The expression that returns null **DOES NOT** fail
- If the failed result is necessary, the ``assert()`` function is necessary
- ``assert(a, a != null)`` -> returns a if a is not null or fails if a is null
- ``assert(b, b >= 0, "'b' should be positive")``