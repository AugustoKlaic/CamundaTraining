# Control flow

### If conditions
- syntax: ``if c then a else b``
- Executes the expression a if the condition c evaluates to true. Otherwise, it executes the expression b
- If the condition c doesn't evaluate to a boolean value, like a null value, it executes the expression b
- ``(if 12 < 10 then "low" else "high") = "high"``

### For loops
- syntax: ``for a in b return c``
- Iterates over the list b and executes c
- Current element is assigned to a
- Results in a list
- If multiple lists are passed to for loop, it iterates over the cross-product of the elements in the lists
  - ``(for x in [1,2], y in [3,4] return x * y) = [3, 4, 6, 8]``
- Instead of a list, the for loop can also iterate over a given range
- Can get previous values of the list result set, use the operator ``partial[-x]``
  - ``for i in 1..10 return if (i <= 2) then 1 else partial[-1] + partial[-2] = [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]``