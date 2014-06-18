# Java EE 6 Advanced Training - Arquillian

## Install the Arquillian Addon on Forge

## Generate the project with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Make sure the `jbossHome` variable in `before/02-Arquillian/generate.fsh` is pointing to `$WILDFLY_HOME`
* Execute the `generate.fsh` script with the command `run ../before/02-Arquillian/generate.fsh` 

## Write the JSB backing bean tests

* Copy the files `PublisherBeanTest.java` and `MusicianBeanTest.java` to the `view` package
* `cp ../before/02-Arquillian/PublisherBeanTest.java cdbookstore/src/test/java/org/agoncal/training/javaee6adv/view`
* `cp ../before/02-Arquillian/MusicianBeanTest.java cdbookstore/src/test/java/org/agoncal/training/javaee6adv/view`
* Code the other tests following the same logic until all the tests pass

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed
* If you have the following error, it's because you are not using the Arquillian Maven profile `DeploymentScenario contains a target (_DEFAULT_) not matching any defined Container in the registry`
* `NoClassDefFoundError` means that your ShrinkWrap packaging misses some classes (check the `createDeployment` method) 
* `ConstraintViolationException` is thrown when the entity is not valid

## Debug the tests if needed

* Make sure WildFly has the debug settings `JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"`

## Execute the tests in a managed environment

* Stop WildFly
* Make sure the `jbossHome` variable in `cdbookstore/src/test/resources/arquillian.xml` is pointing to `$WILDFLY_HOME`
* `mvn -Parquillian-wildfly-managed test` will execute the tests managing WildFly

## Build the application

* In Forge enter the command `build` 

## Deploy the application on WildFly application server

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* Go to the [admin console](http://localhost:9990/)
* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

