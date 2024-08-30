## Camunda Developer certification training

The objective of this repository is to bring together all the information about camunda developer certification. 
Here you will have BPMN diagrams, Java code about connectors and handlers, unit testing of BPMN processes. 
This all with the libs necessary to run with springboot and the correct versions of it and with Gradle build script, for those who hates maven XML format like me hahahahaha.

### Create a camunda 8 account
 - https://signup.camunda.com/

   ```(this is necessary to use the camunda cloud and create a cluster for remote access, you can create a trial account)```

### Create a Camunda Academy account
- https://academy.camunda.com/

  ```(You have to create an account too, don't know if it is free because I used a partner company code to create mine)```

#### Points of attention
- For the libs version you use in gradle or maven, verify your camunda cluster version to use the same version on the libs
- When creating a cluster select all the checkboxes to give permission to all camunda 8 features when remote accessing it with your Java application (it will save you some hours of debugging hahahaha)
