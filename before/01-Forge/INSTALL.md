# Java EE 6 Advanced Training - Forge

## Generate the project with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Execute the `generate.fsh` script with the command `run ../before/01-Forge/generate.fsh` 
* Go to the `cdbookstore` directory

## Add data to the database
 
* Copy the file `import.sql` to `src/main/resources`
* `cp ../before/01-Forge/import.sql src/main/resources`

## Build the application

* In Forge enter the command `build` 

## Deploy the application on WildFly application server

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* Go to the [admin console](http://localhost:9990/)
* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_

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
