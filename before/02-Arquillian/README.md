# Java EE 6 Advanced Training - Arquillian

In this module you will write Arquillian tests for the JSF backing beans and REST endpoints. 

# Preparation

## Check

* Check the `generate.fsh` script
* Check the test classes to be copied (JSF backing bean and REST endpoint)

## Install the Arquillian Addon on Forge

* Make sure the `arquillian-addon` is in the Maven directory
* In Forge, execute the following command `addon-install-from-git --url https://github.com/forge/addon-arquillian.git --coordinate org.arquillian.forge:arquillian-addon`

## Generate the project with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Make sure the `jbossHome` variable in `before/02-Arquillian/generate.fsh` is pointing to `$WILDFLY_HOME`
* Execute the `generate.fsh` script with the command `run ../before/02-Arquillian/generate.fsh` 

# DOJO - Write and pass the AuthorBeanTest

## Write the AuthorBeanTest

* Check the tests `CustomerBeanTest.java`, `MusicianBeanTest.java`, `PublisherBeanTest.java`... that are under `view` package
* Code the `AuthorBeanTest` following the same logic until the test passes
* Create the methods `should_crud` and `should_paginate` 

## Pass the AuthorBeanTest

* Execute only the `AuthorBeanTest` with `mvn -Parquillian-wildfly-remote -Dtest=org.agoncal.training.javaee6adv.view.AuthorBeanTest test`
* If you have the following error, it's because you are not using the Arquillian Maven profile `DeploymentScenario contains a target (_DEFAULT_) not matching any defined Container in the registry`
* `NoClassDefFoundError` means that your ShrinkWrap packaging misses some classes (check the `createDeployment` method) 
* `ConstraintViolationException` is thrown when the entity is not valid

# KATA - Write and pass the AuthorBeanTest, BookBeanTest and CDBeanTest 

# DOJO - Write and pass the AuthorEndpointTest

## Write the AuthorEndpointTest

* Check the tests `CustomerEndpointTest.java` and `MusicianEndpointTest.java` that are under `rest` package
* Code the `AuthorEndpointTest` following the same logic until the test passes
* Create the method `should_be_deployed`
* Package the `persistence.xml` as a resource and the `beans.xml` as WEB-INF resource
* Inject the `baseURL` as an `@ArquillianResource` instead of injecting the bean

## Pass the AuthorEndpointTest

* Execute only the `AuthorEndpointTest` with `mvn -Parquillian-wildfly-remote -Dtest=org.agoncal.training.javaee6adv.rest.AuthorEndpointTest test`
* If you have the following error, it's because you are not using the Arquillian Maven profile `DeploymentScenario contains a target (_DEFAULT_) not matching any defined Container in the registry`
* `NoClassDefFoundError` means that your ShrinkWrap packaging misses some classes (check the `createDeployment` method) 
* `ConstraintViolationException` is thrown when the entity is not valid

# KATA - Write and pass the AuthorEndpointTest, BookEndpointTest and CDEndpointTest 

# Build and run the application

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

## Debug the tests if needed

* Make sure WildFly has the debug settings `JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"`

# Restitution

* Tests can use container services such as Injection
* `@RunAsAclient`
* Configuration in arquillian.xml
* Profile in pom.xml
* Shrinkwrap packaging with `add` methods (`addClass`, `addPackage`...)

# Backup your code

* Save a backup of your code at `../after/02-Arquillian/`
