# Contexts

## Expressions

Creates a new context with given entries.
Each entry has a key and a value.
The key is either a string or a name.
The value can be any type.

### Literal

- Create a context
- A context value can embed other context values
- Inside a context, previous entries can be accessed 
- ``{ a : 1, b : 2}`` or ``{a:2, b: (a*2)}`` or ``{a:1, b:{c:2}}``

### Get entry or path

- Syntax: ``a.b``
- Accesses the entry with the key b of the context a
- Path separated by dot ``.``
- Path can be chained if the value of entry b is also a context: ``a.b.c``
- With the context a doesn't contain an entry with the key b, the expression returns null
- ``{a:2}.a = 2`` or ``{a: {b: 3}}.a = {b:3}`` or ``{a: {b: 3}}.a.b = 3`` or ``{a: 1}.b = null``

### Filter

- Syntax: ``a[c]``
- Filters the list of context a by the condition c 
- The result is a list containing all elements where the condition c is true
- While filtering the current element is assigned to variable item, and its entries can be accessed by the key
- ``[{a:"p1", b:5}, {a:"p2", b:10}][b > 7] = [{a:"p2", b:10}]``

### Projection

- Syntax: ``a.b``
- Extracts the entries with the key b of the list of context elements a
- Returns a list with values of the context elements with the key b
- If an element of the list a does not contain an entry with key b, the result contains null of this element
- ``[{a:"p1", b:5}, {a:"p2", b:10}].a = ["p1", "p2"]`` or ``[{a:"p1", b:5}, {a:"p2", c:20}].b = [5, null]``

