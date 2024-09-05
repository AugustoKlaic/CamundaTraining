# Exercises
## Contexts

### Data types
- Create a context using the "context input" with a key company which includes the keys name, email, and website
  - ```
    {
        "company" : {
                    "name" : "XYZ",
                    "email" : "XYZ@gmail.com",
                    "website" : "www.xyz.com"
                  }
    }
    ```

### Expressions
- Create an expression to access the email of the company from this provided context:
  ```{
    "company": {
        "name": "Camundanzia",
        "email": "hi@camundanzia",
        "website": "camundanzia.com"
    }
  }
  ```
  - ``company.email``


- Create the same context using an expression to return the email of the company
  - ```{
    "company": {
        "name": "Camundanzia",
        "email": "hi@camundanzia",
        "website": "camundanzia.com"
      }
    }.company.email
    ```
- Get the employees with a salary greater than 2000 from this provided context:
  ```
  {
    "employees":[  
        {"name":"Shyam", "email":"shyamjaiswal@gmail.com", "salary":3000},  
        {"name":"Bob", "email":"bob32@gmail.com", "salary":1500},  
        {"name":"Jai", "email":"jai87@gmail.com", "salary":2300}  
    ]
  }
  ```
  - ``employees[salary > 2000]``
- Get the name of the employees with a salary greater than 2000 of previous context
  - ``employees[salary > 2000].name``

### Get Value
- Use the dynamic key list stored in keyPath to retrieve the value from the settings context:
  ```
  {
    "settings": {
        "theme": "dark",
        "preferences": {
            "notifications": {
                "email": "info@camunbankia.com",
                "sms": 4567
            }
        },
        "keyPath": ["preferences", "notifications", "email"]
    }
  }
  ```
  - ``get value(settings, settings.keypath)``
  

- Given the following context, retrieve the "age":
  ``` 
  {
    "person": {
      "details": {
        "name": "John",
        "age": 35
      }
    }
  }
  ```
    - ``get value(person, ["details", "age"])``  

### Get Entries
- Use get entries() to convert the car context into a list of entries
  ```
  {
    "car": {
      "make": "Tesla",
      "model": "Model 3",
      "year": 2020
    }
  }
  ```
  - ``get entries(car)``


- How many entries has this book context? You can use the count() function
  ```
  {
    "book": {
        "title": "1984",
        "author": "George Orwell",
        "year": 1949
    }
  }
  ```
  - ``count(get entries(book))``

### Context Put
- Update the value of the "status" key to "Completed" in the project context
  ```
  {
    "project": {
        "name": "Apollo",
        "status": "In Progress",
        "duration": 12
    }
  }
  ```
  - ``context put(project, "status", "Completed")``


- Add keys "location" with value "UK" and "genre" with value "Dystopian" to the book context
  ```
  {
    "book": {
        "title": "1984",
        "author": "George Orwell"
    }
  }
  ```
  - ``context put(context put(book, "location", "UK"), "genre", "Dystopian")``


- Add a new entry "publication" with keys "year" with value "1949" and "publisher" with value "Secker & Warburg" to the same previous book context
  - ``context put(book, "publication", {"year" : 1949, "publisher" : "Secker & Warburg"})``


### Context Merge
- Merge context1 and context2, and observe how duplicate keys are handled
  ```
  {
    "context1": {
        "employeeId": 123,
        "name": "Emily",
        "department": "HR"
    },
    "context2": {
        "name": "Emma",
        "age": 28,
        "department": "Finance"
    }
  }
  ```
  - ``context merge(context1, context2)``


- Merge context1 and context2 to see how nested contexts are combined
  ```
  {
    "context1": {
        "project": {
            "name": "Apollo",
            "status": "In Progress"
        }
    },
    "context2": {
        "project": {
            "deadline": "2024-12-31",
            "status": "Completed"
        }
    }
  }
  ```
  - ``merge(context1, context2)``

### Challenge
- Get the context skills from the senior teachers (with at least 10 years of experience)
  ```
  {
  "teachers": [
    {
      "name": "John Doe",
      "yearsOfExperience": 5,
      "skills": {
        "Classroom Management": "Intermediate",
        "Curriculum Development": "Basic",
        "Mathematical Analysis": "Expert"
      }
    },
    {
      "name": "Jane Smith",
      "yearsOfExperience": 7,
      "skills": {
        "Literary Criticism": "Expert",
        "Creative Writing": "Intermediate",
        "Public Speaking": "Basic"
      }
    },
    {
      "name": "Emily Johnson",
      "yearsOfExperience": 4,
      "skills": {
        "Lab Safety": "Intermediate",
        "Biology Research": "Basic",
        "Student Engagement": "Intermediate"
      }
    },
    {
      "name": "Michael Brown",
      "yearsOfExperience": 10,
      "skills": {
        "Historical Research": "Expert",
        "Archival Studies": "Expert",
        "Lesson Planning": "Intermediate"
      }
    },
    {
      "name": "Jessica Garcia",
      "yearsOfExperience": 3,
      "skills": {
        "Chemical Analysis": "Basic",
        "Safety Procedures": "Intermediate",
        "Problem Solving": "Basic"
      }
    },
    {
      "name": "William Martinez",
      "yearsOfExperience": 6,
      "skills": {
        "Physical Fitness": "Expert",
        "Sports Coaching": "Intermediate",
        "Team Building": "Intermediate"
      }
    },
    {
      "name": "Sarah Wilson",
      "yearsOfExperience": 9,
      "skills": {
        "Artistic Expression": "Expert",
        "Design Fundamentals": "Intermediate",
        "Art History": "Basic"
      }
    },
    {
      "name": "Brian Anderson",
      "yearsOfExperience": 8,
      "skills": {
        "Theoretical Physics": "Expert",
        "Experimental Design": "Intermediate",
        "Quantitative Analysis": "Expert"
      }
    },
    {
      "name": "Nancy Davis",
      "yearsOfExperience": 2,
      "skills": {
        "Music Theory": "Intermediate",
        "Instrumental Skills": "Basic",
        "Performance Techniques": "Basic"
      }
    },
    {
      "name": "David Miller",
      "yearsOfExperience": 11,
      "skills": {
        "Programming": "Expert",
        "Algorithm Design": "Expert",
        "Cybersecurity": "Intermediate"
      }
    }
  ]
  }
  ```
  - ``context merge(teachers[yearsOfExperience >= 10].skills)``