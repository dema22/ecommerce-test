
# Simple back-end application for an e-commerce cart system using Java.


# Project Setup

   1. Build Tool: Use Maven for dependency management and build automation.
   2. Framework: Use Spring Boot for quick setup, REST API creation, and dependency injection.
   3. Testing: Use JUnit and Mockito for unit and integration tests.

# Application Architecture

   1. Controllers: Handle HTTP requests and responses.
   2. Services: Implement the business logic.
   3. Models: Represent the data structures (e.g., Cart, Product).
   4. Repositories: Handle the in-memory storage of data.
   5. Scheduler: Handle automatic deletion of inactive carts.


# Pre-Requisites

In order to run the app, you will need:
```
  Docker-compose
```
# How to Running The App

1. cd into ecommerce
2. Do `docker-compose build`
3. Do `docker-compose up`
4. For stopping the app : Kill the container gracefully with control+C or do `docker-compose down`


# How to Consume The App

You can  go to https://editor.swagger.io/ and paste the content of the swagger-docs.yaml file that is in the root of the project. 
Then you can start sending request to the API.

# Run unit and integration tests
1. cd into ecommerce
2. Run the following command: `./mvnw test`