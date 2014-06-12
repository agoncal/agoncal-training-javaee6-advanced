# Java EE 6 Advanced Training - Forge

## Generate the project with JBoss Forge

* Launch JBoss Forge (enter the `forge` command)
* Execute the `generate.fsh` script with the command `run before/01-Forge/generate.fsh` 

## Add data to the database
 
* Copy the file `before/01-Forge/import.sql` to `cdbookstore/src/main/resources`

## Deploy the application on JBoss application server

* Start JBoss (`JBOSS_HOME/bin/standalone.sh`)
* Deploy the `cdbookstore/target/cdbookstore.war` file

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

## Check the REST interfaces

* The following REST interfaces are defined
* [http://localhost:8080/cdbookstore/rest/authors]()
* [http://localhost:8080/cdbookstore/rest/books]()
* [http://localhost:8080/cdbookstore/rest/cds]()
* [http://localhost:8080/cdbookstore/rest/musicians]()
* Use [PostMan REST client](https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm) to create/update/remove entities

## Check the code of the application

### Entity Layer

* Implement `Serializable` 
* Use the `@XmlRootElement` JAXB annotation
* Use of Bean Validation constraint
* Optimistic locking with `@Version` 

### JSF Backing Beans

* Represented as `@Stateful` EJBs
* Uses the `@ConversationScoped` and `Conversation` API of CDI
* Uses an extended persistence context `PersistenceContextType.EXTENDED`
* Check the `faces-redirect=true`
* Uses the JPA Criteria API on `paginate`, `getSearchPredicates` and `getAll` methods
* Uses a `Converter` to display a bean on a JSF page

### REST Interfaces

* Use the JPA `Predicate` and `CriteriaBuilder` APIs
* Configuration done with `RestApplication`
* Methods return the `Response` API
