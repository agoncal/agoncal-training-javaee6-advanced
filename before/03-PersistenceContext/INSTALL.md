# Java EE 6 Advanced Training - PersistenceContext

## Start WildFly application server

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`

## Build the application

* In Forge enter the command `build` 

## Deploy the application on WildFly application server

* Go to the [admin console](http://localhost:9990/)
* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

## Check the extended persistence context

* [http://localhost:8080/cdbookstore/faces/admin/CD/search.xhtml]()
* [http://localhost:8080/cdbookstore/faces/admin/CD/view.xhtml?id=-1]()
* [http://localhost:8080/cdbookstore/faces/admin/musician/view.xhtml?id=-4]()

## Change all the persistence context from extended to transactional

* Change all `@PersistenceContext(unitName = "cdbookstorePU", type = PersistenceContextType.EXTENDED)` to `@PersistenceContext(unitName = "cdbookstorePU")`
* Displaying and creating CDs from the UI should fail

## Get rid of lazy initialization exception by finding by id with relations

* Book and CD have relations with other entities
* Both `BookBean.findById` and `CDBook.findById` should use LEFT joins to get relations

## Check the transactional persistence context and make sure it's working

* [http://localhost:8080/cdbookstore/faces/admin/CD/search.xhtml]()
* [http://localhost:8080/cdbookstore/faces/admin/CD/view.xhtml?id=-1]()
* [http://localhost:8080/cdbookstore/faces/admin/musician/view.xhtml?id=-4]()

## Execute the tests in a remote environment

* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed
