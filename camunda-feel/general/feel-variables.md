# Variables

- Access the variable by its name
- If variable is a context, you can access its entries by its keys: ``a.b``
- If no variables exist with given name, returns null
- Use null-check when the variable can be null: ``a != null and a.b > 10``

### Variables names
- You can use **camelCase** ou **snake_case**
- **kebab-case** cannot be used, because it has the ``-`` symbol
- It may not start with numbers: ``1stChoice``
- It may not contain whitespaces: ``order number``
- It may not contain operators: ``+, =, -, /, *, ?, ., <, >``
- It may not be a literal: ``null, if, true, function``...