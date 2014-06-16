# Java EE 6 Advanced Training - Service Tier

## Generate the service tier with JBoss Forge

* Launch JBoss Forge (enter the `forge` command)
* Go to the `cdbookstore` directory
* Execute the `generate.fsh` script with the command `run before/04-Service/generate.fsh` 

## Add producers to the Resources class

* Produce the `EntityManager`
* Produce a `Logger` with an `InjectionPoint`

## Write some tests

* Copy the file `PublisherServiceTest.java` to `cdbookstore/src/test/java/org/agoncal/training/javaee6adv/service`
* `run -c "cp before/04-Service/PublisherServiceTest.java cdbookstore/src/test/java/org/agoncal/training/javaee6adv/service`
* Code the other tests following the same logic

## Code the services until the tests pass

* Copy the file `before/04-Service/AbstractService.java` to `cdbookstore/src/main/java/org/agoncal/training/javaee6adv/service`
* Services should extend `AbstractService`
* Get rid of `EntityManager` in REST enpoints and instead call the services
* Get rid of `EntityManager` in JSF backing beans and instead call the services
* Add the produced `EntityManager` to the services
* Get rid of `SessionContext` in JSF Backing beans (why do we have `sessionContext.getBusinessObject(AuthorBean.class)`)

## Execute the tests

* `mvn -Parquillian-wildfly-managed test` will execute the tests within WildFly in a managed way
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed
* If you have the following error, it's because you are not using a Maven profile `DeploymentScenario contains a target (_DEFAULT_) not matching any defined Container in the registry`

## Deploy the application on WildFly application server

* Start WildFly (`WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* Deploy the `cdbookstore/target/cdbookstore.war` file

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

