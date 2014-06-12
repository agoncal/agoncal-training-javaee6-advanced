# Java EE 6 Advanced Training - Service Tier

## Generating the JSF pages and backing beans with JBoss Forge

* Execute the `generate.fsh` script.

## Add Bootstrap as an external resource

* Delete `cdbookstore/src/main/resources/bootstrap.css`
* Add Bootstrap as a resource in `cdbookstore/src/main/webapp/resources/scaffold/pageTemplate.xhtml`
* `<h:outputStylesheet library="webjars/bootstrap/2.3.2/css" name="bootstrap.css"/>`

## Copy the new JSF template

Copy the `mainTemplate.xhtml` package to `cdbookstore/src/main/webapp/resources/scaffold`

## Copy the Arquillian tests for the service

Copy the `service` package to `cdbookstore/src/main/test/org/agoncal/training/javaee6adv`

## Deploy the application on JBoss application server


## Check the web application

* Go to [http://localhost:8080/cdbookstore]()

## Check JSF debug information

* Press `CTRL-SHIFT-D` thanks to `javax.faces.PROJECT_STAGE` in `web.xml`

## Check WebJar and JSF Resources



