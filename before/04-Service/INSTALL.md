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
* Add the produced `EntityManager` to the services
* Get rid of `SessionContext` in JSF Backing beans (why do we have `sessionContext.getBusinessObject(AuthorBean.class)`)

## Deploy the application on JBoss application server


## Check the web application

http://localhost:8080/cdbookstore

