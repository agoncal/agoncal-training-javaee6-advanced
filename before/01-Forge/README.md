# Java EE 6 Advanced Training - Forge

In this module you will generate the CDBookStore application. It is a full Java EE 6 application with a JSF user interface and a REST interface. Data is stored into a H2 in-memory database. Thanks to JBoss Forge, generating the entire application takes only a few seconds. 

# DOJO - Generate the project with JBoss Forge

## Generate the project with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Execute the `generate.fsh` script with the command `run before/01-Forge/generate.fsh` 

# Build and run the application

* Exit JBoss Forge (`exit` command)
* Go to the `cdbookstore` directory
* Use Maven to build the application with `mvn clean install`

## Deploy the application on WildFly application server

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* JBoss Console
	* Go to the [admin console](http://localhost:9990/)
	* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_
* or JBoss CLI
	* Execute JBoss CLI : `$WILDFLY_HOME/bin/jboss-cli.sh`
	* Connect to the server by entering : `connect` 
	* Deploy the war : `deploy cdbookstore/target/cdbookstore.war --force`  

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()
* Browse pages to create/update/remove entities

## Check the REST interfaces

* The following REST interfaces are defined
* [http://localhost:8080/cdbookstore/rest/authors]()
* [http://localhost:8080/cdbookstore/rest/books]()
* [http://localhost:8080/cdbookstore/rest/cds]()
* [http://localhost:8080/cdbookstore/rest/customers]()
* [http://localhost:8080/cdbookstore/rest/musicians]()
* [http://localhost:8080/cdbookstore/rest/purchaseorders]()
* Use [PostMan REST client](https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm) to create/update/remove entities

# KATA - Generate the project with JBoss Forge

# Restitution

## Entity Layer

* Implement `Serializable` 
* Use the `@XmlRootElement` JAXB annotation
* Use of Bean Validation constraint
* Optimistic locking with `@Version` 

## JSF Backing Beans

* Represented as `@Stateful` EJBs
* Uses the `@ConversationScoped` and `Conversation` API of CDI
* Uses an extended persistence context `PersistenceContextType.EXTENDED`
* Check the `faces-redirect=true`
* Uses the JPA Criteria API on `paginate`, `getSearchPredicates` and `getAll` methods
* Uses a `Converter` to display a bean on a JSF page

## REST Interfaces

* Use the JPA `Predicate` and `CriteriaBuilder` APIs
* Configuration done with `RestApplication`
* Methods return the `Response` API

# Backup your code

* Save a backup of your code at `../after/01-Forge/`
