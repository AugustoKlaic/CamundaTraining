# Exercises
## Contexts

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