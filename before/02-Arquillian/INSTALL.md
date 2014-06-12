# Java EE 6 Advanced Training - Arquillian

## Installing the Arquillian Addon on Forge

* Launch JBoss Forge (enter the `forge` command)
* Execute the `generate.fsh` script with the command `run before/02-Arquillian/generate.fsh` 

## Write some tests

* Copy the file `before/02-Forge/PublisherBeanTest.java` to `cdbookstore/src/test/java/org/agoncal/training/javaee6adv/view`


## Write the tests

* `mvn -Parquillian-jbossas-managed-7 test` will execute the tests within JBoss in a managed way
* `mvn -Parquillian-jbossas-remote-7 test` will execute the tests with JBoss up and running and with the application deployed
* If you have the following error, it's because you are not using a Maven profile `DeploymentScenario contains a target (_DEFAULT_) not matching any defined Container in the registry`

## Deploy the application on JBoss application server

* Start JBoss (`JBOSS_HOME/bin/standalone.sh`)
* Deploy the `cdbookstore/target/cdbookstore.war` file

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

