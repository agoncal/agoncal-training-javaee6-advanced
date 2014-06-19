# Java EE 6 Advanced Training - Service Tier

## Generate the service tier with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Execute the `generate.fsh` script with the command `run ../before/04-Service/generate.fsh` 

## Add producers to the Resources class

* The class `org.agoncal.training.javaee6adv.util.Resources` should produce an `EntityManager` and a `Logger` 
* Produce the `EntityManager` for `cdbookstorePU`
* Produce a `java.util.logging.Logger` with an `InjectionPoint`

## Write the services

* Copy the files `AbstractService.java`, `MusicianService.java` and `PublisherService.java` to `cdbookstore/src/test/java/org/agoncal/training/javaee6adv/service`
* `cp ../before/04-Service/AbstractService.java src/main/java/org/agoncal/training/javaee6adv/service`
* `cp ../before/04-Service/MusicianService.java src/main/java/org/agoncal/training/javaee6adv/service`
* `cp ../before/04-Service/PublisherService.java src/main/java/org/agoncal/training/javaee6adv/service`
* Code the other services following the same logic until all the tests pass
* Services should extend `AbstractService` (which uses the produced `EntityManager`) 
* Each service overides the `getSearchPredicates` method

## Refactor the JSF backing beans and REST endpoints

### REST endpoints

* In REST enpoints get rid of `EntityManager` and instead inject the appropriate service
* REST enpoints don't need to be transactional now

### JSF backing beans

* In JSF backing beans get rid of `EntityManager` and instead inject the appropriate service
* Each call to the `entityManager` variable has to be replaced by a call to the service (eg. `entityManager.find` replaced by `service.findById`)
* Why can we get rid of the `entityManager.flush` call ?
* Methods `getSearchPredicates` are move to the services
* To `paginate` user `service.count` and `service.page`
* Remove `SessionContext` (why do we have `sessionContext.getBusinessObject(AuthorBean.class)`)
* Remove `ejbProxy` and replace `ejbProxy.findById` by `service.findById`  
* Backing beans don't need to be transactional now

## Write the service tests

* Copy the files `MusicianService.java` and `PublisherServiceTest.java` to the `service` package
* `cp ../before/04-Service/MusicianService.java cdbookstore/src/test/java/org/agoncal/training/javaee6adv/service`
* `cp ../before/04-Service/PublisherServiceTest.java cdbookstore/src/test/java/org/agoncal/training/javaee6adv/service`
* Code the other tests following the same logic until all the tests pass

## Refactor the existing tests

* JSF backing beans test need now to add service dependencies in the packaging (eg. `Resources`, `AbstractService`, `AuthorService`)
* REST endpoint tests need now to add service dependencies in the packaging (eg. `Resources`, `AbstractService`, `AuthorService`)

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed
* If you have the following error, it's because you are not using the Arquillian Maven profile `DeploymentScenario contains a target (_DEFAULT_) not matching any defined Container in the registry`
* `NoClassDefFoundError` means that your ShrinkWrap packaging misses some classes (check the `createDeployment` method) 
* `ConstraintViolationException` is thrown when the entity is not valid

## Debug the tests if needed

* Make sure WildFly has the debug settings `JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"`

## Build the application

* In Forge enter the command `build` 

## Deploy the application on WildFly application server

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* Go to the [admin console](http://localhost:9990/)
* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

