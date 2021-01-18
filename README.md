# contrast-api
Contrast API for Java Engineer

The system has three objects:
  Platforms
  Applications
  Organizations
 
The objects relate to each other like so:
  Applications have one Platform
  Organizations have many applications
 
## The API

The following API calls are available:
 
GET /organizations

GET /organizations/{id}

GET /organizations/{id}/applications

GET /organizations/{id}/applications?query=gateway

GET /organizations/{id}/applications?query=gateway&order=’name asc’

## Compiling the project

    ./mvnw install

This will run all unit tests and integration tests as well as install to your m2 repo.

To run Integration tests the app uses test containers.
This has a dependency on you having Docker installed.
If you don't have Docker you can change the property spring.datasource.url to point at another DB for testing e.g. H2

## Running the project

This project comes with a docker compose file which will create a MySQL db, populate it and start the contrast api container.

To start the application run :

    docker-compose up -d

The application will be available at http://localhost:8080

