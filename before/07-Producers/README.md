# Java EE 6 Advanced Training - Producing Java EE Resources

Thanks to CDI producers, we can now produce Java EE resources and inject them

# Preparation

## Check

* Check the `generate.fsh` script

## Generate the CDI ResourceProducer with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Execute the `generate.fsh` script with the command `run ../before/07-Producers/generate.fsh` 

# DOJO - Produce the EntityManager, FacesContext and a Logger 

## Produce and inject the entity manager

* The class `org.agoncal.training.javaee6adv.util.ResourceProducer` should produce the `EntityManager` for `cdbookstorePU`
* The `AbstractService` should inject the `EntityManager` with `@Inject` instead of `@PersistenceContext` 

## Produce and inject a logger

* The class `org.agoncal.training.javaee6adv.util.ResourceProducer` should produce a `java.util.logging.Logger`
* Use the `javax.enterprise.inject.spi.InjectionPoint` API to get the class name to log
* The `AbstractService` should inject the `Logger` with `@Inject` and log a message in the `persist` and `remove` method 

## Produce and inject the FacesContext

* The class `org.agoncal.training.javaee6adv.util.ResourceProducer` should produce the `javax.faces.context.FacesContext`
* All the Bean classes (e.g. `AuthorBean`, `BookBean`, `CategoryBean`...) should inject the `FacesContext` with `@Inject`
* All the Bean classes should use the injected `FacesContext` instead of building it with `FacesContext.getCurrentInstance()`
* Careful because Beans `implement Serializable`. You might use CDI scopes

## Refactor the tests

* All tests now need to `addClass(ResourceProducer.class)`

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# KATA - Produce the EntityManager, FacesContext and a Logger 

## Build the application

* Use Maven and build the application with `mvn clean install`

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

## Check the CDBook-Client application is running

* Go to `cdbookclient` directory
* Run `mvn install` which executes the `Main` class

# Restitution

* Produces
* Scopes
* Serializable vs transient scopes

# Backup your code

* Save a backup of your code at `../after/07-Producers/`
