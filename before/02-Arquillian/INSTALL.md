# Java EE 6 Advanced Training - Arquillian

## Installing the Arquillian Addon on Forge

* Launch JBoss Forge (enter the `forge` command)
* Execute the `generate.fsh` script with the command `run before/02-Arquillian/generate.fsh` 

## Write some tests

* Copy the file `before/02-Forge/PublisherBeanTest.java` to `cdbookstore/src/test/java/org/agoncal/training/javaee6adv/view`
* Code the other tests following the same logic

## Start JBoss application server

* Start JBoss (`JBOSS_HOME/bin/standalone.sh`)
* Make sure JBoss has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`

## Execute the tests

* `mvn -Parquillian-jbossas-remote-7 test` will execute the tests with JBoss up and running and with the application deployed
* If you have the following error, it's because you are not using a Maven profile `DeploymentScenario contains a target (_DEFAULT_) not matching any defined Container in the registry`

## Debug the tests

* Make sure JBoss has the debug settings `JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"`

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

