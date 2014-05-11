# Java EE 6 Advanced Training - Service Tier

## Generating the service tier with JBoss Forge

Execute the `generate.fsh` script.

## Copy the Arquillian tests for the service

Copy the `service` package to `cdbookstore/src/main/test/org/agoncal/training/javaee6adv`

## Add producers to the Resources class

* Produce the `EntityManager`
* Produce a `Logger` with an `InjectionPoint`

## Code the services until the tests pass

* Get rid of `EntityManager` in REST enpoints and instead call the services
* Get rid of `EntityManager` in JSF backing beans and instead call the services
* Add the produced `EntityManager` and `Logger` to the services

## Deploy the application on JBoss application server


## Check the web application

http://localhost:8080/cdbookstore

