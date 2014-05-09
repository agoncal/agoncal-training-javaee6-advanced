# Java EE 6 Advanced Training - Installation guide

## Generating the project with JBoss Forge

Execute the `02-Forge/generate.fsh` script.

## Adding data to the database

Copy the `import.sql` file to `src/main/resources`

## Deploy the application on JBoss application server


## Check the web application

http://localhost:8080/cdbookstore

## Check the REST interfaces

http://localhost:8080/cdbookstore/rest/authors
http://localhost:8080/cdbookstore/rest/books
http://localhost:8080/cdbookstore/rest/cds
http://localhost:8080/cdbookstore/rest/musicians

## JSF Backing Beans

* Represented as `@Stateful` EJBs
* Uses the `@ConversationScoped` and `Conversation` API of CDI
* Uses an extended persistence context `PersistenceContextType.EXTENDED`
* Check the `faces-redirect=true`
* Uses the JPA Criteria API on `paginate`, `getSearchPredicates` and `getAll` methods
* Uses a `Converter` to display a bean on a JSF page