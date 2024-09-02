# Lists

## Expressions

List expressions creates a new List of given elements.
Elements can be of any type.

### Literal
- A list can embed other list values
- ``[1,2,3,4]`` or ``[[1,2], 3, [4,5]]``

### Get element
- Syntax: ``a[i]``
- Access an element of the list x at index i.
- Index starts at 1.
- Negative index, the counting starts from the end of the list
- Last element of the list is ``-1``
- Accessing the position 0 of the list returns ``null``
- Accessing a position bigger then the size of the array returns ``null``
- ``[9,8,7,6][2] = 8`` or ``[9,8,7,6][7] = null`` or ``[9,8,7,6][0] = null`` or ``[9,8,7,6][-1] = 6``

### Filter element
- Syntax: ``a[c]``
- Filter the list x by condition c
- The result of the expression is a list where all elements match the condition c
- Other elements are excluded from the resultant list
- While filter occur, the current element is assigned to the variable item
- ``[9,8,7,6][item < 7] = [6]`` or ``[1,2,3,4][even(item)] = [2,4]`` or ``[9,8,7][item > 10] = []``

### Some
- Syntax: ``some a in b satisfies c``
- Iterates over the list b and evaluates condition c for each element in the list
- The current element is assigned to variable a
- Returns true if c evaluates to true for **ONE OR MORE** elements of b, otherwise false
- ``(some x in [1,2,3] satisfies x > 2) = true`` or ``(some x in [1,4,5], y in [2,3] satisfies x < y) = false``

### Every
- Syntax: ``every a in b satisfies c``
- Iterates over the list b and evaluates condition c for each element in the list
- The current element is assigned to variable a
- Return true if c evaluates to true for **ALL** elements of b, otherwise false
- ``(every x in [1,2,3] satisfies x >= 1) = true`` or ``(every x in [1,2], y in [2,3] satisfies x < y) = false``