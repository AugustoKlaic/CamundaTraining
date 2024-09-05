# Exercises
## Control Flow

### Advanced conditionals
- Write a FEEL expression to determine the level of access a user should have based on their role and department. The levels are:
  - Admins in any department: "Full Access"
  - Managers in Sales: "Sales Access"
  - Managers in other departments: "Limited Access"
  - All other roles: "No Access"
    ```
    {
      "customer": {
        "loyaltyPoints": 550,
        "isFirstOrder": false,
        "age": 23,
        "isStudent": true,
        "isMember": false,
        "isPremiumMember": true
      },
      "order": {
        "totalAmount": 250,
        "isGift": false
      },
      "user": {
        "role": "Manager",
        "department": "Sales"
      }
    }
    ```
  - ```
    if user.role = "Admins" then "Full Access" else
      if user.role = "Manager" then
        if user.department = "Sales" then "Sales Access" else "Limited Access" 
      else "No Access"
    ```

- Write a FEEL expression to determine if an order qualifies for a "Special Discount". The order qualifies 
if the customer is a premium member or the order totalAmount is above $200, and the order is not a gift order 
  - ``if (customer.isPremiumMember or order.totalAmount > 200) and not(order.isGift) then "Special Discount" else "No Discount"``


- Write a FEEL expression to add a new element to the customer following this rules:
  - category will be "Adult" if the age is greater than 18 otherwise, category will be "Minor"
  
  - ```
      if customer.age > 18 then context put(customer, "category", "Adult")
        else context put(customer, "category", "Minor")
    ```
    
### Loops and Iterations
- Apply a 15% discount to each price in this list ``[50, 150, 250]``
  - ``for price in [50, 150, 250] return price * 0.85``
  

- Given this context containing different types of services and their respective details, write a FEEL expression 
that iterates through the services and constructs a new list of contexts. Each new context will include a formatted (upper case()) label with the service name and a value based on the service elements.
  ```
    {
      "services": ["training","consulting","support"],
      "training":{
        "format": "Online",
        "roles": ["Developer","Sys Admin"],
        "numPeople": 20
      },
      "consulting":{
        "description":"Project follow-up"
      },
      "support":{
        "type":"gold"
      }
    }
  ```
  - ```
    for s in services
    return {
        "label": upper case(s),
        "value": if(s = "training") then 
                    "Format: " + training.format + " - " + string(training.numPeople) + " people - Roles: " + string join(training.roles, ", ")
                 else if(s = "consulting") then 
                    get value(consulting, "description")
                 else
                    get value(support, "type")
        }
    ```
    
### Custom Functions
- Define a function to create a custom welcome message according to the current time and the user name. e.g. Good Morning, Alice!
  - ```
    {
      username : "Augusto",
      functionDef : function(username) "Hello " + username + ", " 
                        + (if now().hour < 12 then "Good Morning!"
                          else if now().hour < 18 then "Good Afternoon!"
                          else "Good Evening!"),
      message : functionDef(username)
    }.message
    ```

- Define a custom function to return the number of days remaining until a date in the future. e.g. 12 days
  - ```
    {
      dateIntheFuture : date("2034-07-22"),
      functionDef : function(dateInTheFuture) (dateIntheFuture - today()).days,  
      daysRemaining : functionDef(dateInTheFuture)
    }.daysRemaining
    ```
    
### Challenge
```
{
  "processInstance": {
        "id": "P123456789",
        "startDate": "2023-01-01T09:00:00Z",
        "tasks": [
            {"id": "T2", "name": "Gather Requirements", "duration": 120, "completed": true},
            {"id": "T6", "name": "Deployment", "duration": 90, "completed": false},
            {"id": "T4", "name": "Implement Solution", "duration": 240, "completed": false},
            {"id": "T3", "name": "Design Solution", "duration": 180, "completed": false},
            {"id": "T5", "name": "Testing & QA", "duration": 180, "completed": false},
            {"id": "T1", "name": "Initial Assessment", "duration": 60, "completed": true}      
        ],
        "variables": {
        "budget": 10000,
        "expenses": 4500,
        "stakeholders": [
            {"name": "Alice", "role": "Sponsor"},
            {"name": "Bob", "role": "Product Owner"},
            {"name": "Charlie", "role": "Lead Developer"}
        ]
    }
  }
}
```
- Write a FEEL expression to calculate the total duration of all completed tasks. Update the processInstance object by adding a new element totalCompletedDuration with the calculated value.
  - ``context put(processInstance, "totalCompletedDuration", sum(for task in processInstance.tasks[completed = true] return task.duration))``


- Write a FEEL expression that compares the current expenses against the budget and determines the budget status as "Under Budget", "On Budget", or "Over Budget". Update the processInstance variables with a new key-value pair budgetStatus
  - ```
    context put(processInstance.variables, "budgetStatus", 
        if processInstance.variables.budget > processInstance.variables.expenses then "Under Budget"
        else if processInstance.variables.budget < processInstance.variables.expenses then "Over Budget"
        else "On Budget")
    ```

- Write a FEEL expression to find the first uncompleted task in the process sorted by id in alphabetical order. Update the processInstance object by adding a new element nextTask which should be the name of the next task to complete
  - ```
      context put(processInstance, "nextTask", sort(processInstance.tasks[completed = false], function(x, y) x.id < y.id)[1].name)
    ```
    
- Create a custom FEEL function named stakeholderRoles that takes the stakeholders list and returns a list of roles. Use this function to update the processInstance object by adding a new property rolesList that contains the list of roles from the stakeholders.
  - ```
    context put(processInstance, "roleList", 
      {
        getRoleList : function(stakeholders) for stakeholder in stakeholders return stakeholder.role,
        roleList: getRoleList(processInstance.variables.stakeholders)
      }.roleList)
    ```