# Java EE 6 Advanced Training - Web Application

## Generating a few artifacts

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Execute the `generate.fsh` script with the command `run before/06-WebApplication/generate.fsh` 

## Setup a new UI

### Add webjars to get rid of boostrap

* Delete Bootstrap because it's used with Webjars now `rm src/main/webapp/resources/bootstrap.css`

###  Copy the templates, css and logos
 
* `cp ../before/06-WebApplication/mainTemplate.xhtml src/main/webapp/resources/scaffold/`
* `cp ../before/06-WebApplication/pageTemplate.xhtml src/main/webapp/resources/scaffold/`
* `cp ../before/06-WebApplication/banner.png src/main/webapp/resources/`
* `cp ../before/06-WebApplication/forge-logo.png src/main/webapp/resources/`
* `cp ../before/06-WebApplication/forge-style.css src/main/webapp/resources/`
* `cp ../before/06-WebApplication/index.html src/main/webapp/`

###  Copy the book pages

* `cp ../before/06-WebApplication/book src/main/webapp/`

## Copy the Arquillian tests

* Copy the files `BookServiceTest.java` and `CDServiceTest.java` to the `service` package so they can test the services
* `cp ../before/06-WebApplication/BookServiceTest.java cdbookstore/src/test/java/org/agoncal/training/javaee6adv/service`
* `cp ../before/06-WebApplication/CDServiceTest.java cdbookstore/src/test/java/org/agoncal/training/javaee6adv/service`
* Copy the files `BookBeanTest.java` and `CDBeanTest.java` to the `view` package so they can test the backing beans
* `cp ../before/06-WebApplication/BookBeanTest.java cdbookstore/src/test/java/org/agoncal/training/javaee6adv/view`
* `cp ../before/06-WebApplication/CDBeanTest.java cdbookstore/src/test/java/org/agoncal/training/javaee6adv/view`

## Code the services and JSF backing beans until the test execute

* Remember the `@Named` annotation allows a service to be used within a JSF page
* Remember the `findByIdWithRelations` method if you need to load the dependent entities

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed
* If you have the following error, it's because you are not using the Arquillian Maven profile `DeploymentScenario contains a target (_DEFAULT_) not matching any defined Container in the registry`
* `NoClassDefFoundError` means that your ShrinkWrap packaging misses some classes (check the `createDeployment` method) 
* `ConstraintViolationException` is thrown when the entity is not valid

## Debug the tests if needed

* Make sure WildFly has the debug settings `JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"`

## Code the pages for CDs

* The CD pages have to be under `src/main/webapp/cd` :
* `index.xhtml` to visualize all the books
* `navigation.xhtml` lists all the CD genres
* `view.xhtml` shows the details of a CD
* `viewPerGenre.xhtml` shows all the CDs for a particular genre
* Get inspiration from `src/main/webapp/book`

## Build the application

* In Forge enter the command `build` 

## Deploy the application on WildFly application server

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* Go to the [admin console](http://localhost:9990/)
* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

## Check JSF debug information

* Press `CTRL-SHIFT-D` thanks to `javax.faces.PROJECT_STAGE` in `web.xml`

## Check the CDBook-Client application is running

* Go to `cdbookclient` directory
* Run `mvn install` which executes the `Main` class
