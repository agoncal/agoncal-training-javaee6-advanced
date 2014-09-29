# Java EE 6 Advanced Training - PostGres

## Deploy a Postgres Datasource on WildFly

* Make sure WildFly is shutdown
* copy the directory `postgresql` to `$WILDFLY_HOME/modules/system/layers/base/org`
* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Execute the `setup.cli` script
* Execute `$WILDFLY_HOME/bin/jboss-cli.sh`, connect to the running WildFly (`connect`)

## Generate the project with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Execute the `generate.fsh` script with the command `run ../before/01-Forge/generate.fsh` 
* Go to the `cdbookstore` directory

## Refactor the domain model

* Book and CD should extend Item 
* `Item` attributes should be protected instead of private
* `Book` and `CD` extend `Item`
* Get rid of redundant attributes in `Book` and `CD`
* Move `@Id`, `@Version`, getters and setters... from child class to `Item` 
* Add `Item` to the packaging of the tests `BookEndpointTest`, `CDEndpointTest`, `BookServiceTest`, `CDServiceTest` 

* If you have the following exception it's because you have imports with `*`
* `Exception when parsing/running: jpa-new-entity --named Address , No instances of [org.jboss.forge.roaster.spi.WildcardImportResolver] were found on the classpath.`

## Refactor the service tier

* `CustomerService` and `OrderService` should extend from `AbstractService` (we don't need `getSearchPredicates`, just make it return null)

## Build the application

* In Forge enter the command `build` 

## Deploy the application on WildFly application server

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* Go to the [admin console](http://localhost:9990/)
* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

## Check the CDBook-Client application is running

* Go to `cdbookclient` directory
* Run `mvn install` which executes the `Main` class

## Backup your code

* Save a backup of your code at `../after/07-ShoppingCart/`
