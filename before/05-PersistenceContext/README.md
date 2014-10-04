# Java EE 6 Advanced Training - PersistenceContext

Extended persistence context allow entities to be managed longer than a transaction and do not need to be merge.

# Preparation

## Deploy the application on WildFly application server

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* JBoss Console
	* Go to the [admin console](http://localhost:9990/)
	* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_
* or JBoss CLI
	* Execute JBoss CLI : `$WILDFLY_HOME/bin/jboss-cli.sh`
	* Connect to the server by entering : `connect` 
	* Deploy the war : `deploy cdbookstore/target/cdbookstore.war --force`  

## Check the extended persistence context

* [http://localhost:8080/cdbookstore/faces/admin/CD/search.xhtml]()
* [http://localhost:8080/cdbookstore/faces/admin/musician/view.xhtml?id=-4]()
* [http://localhost:8080/cdbookstore/faces/admin/CD/view.xhtml?id=-13]()
* Everything should be ok

# DOJO - Change all the persistence context from extended to transactional

## Change all the persistence context from extended to transactional

* In every JSF backing bean, change all `@PersistenceContext(unitName = "cdbookstorePU", type = PersistenceContextType.EXTENDED)` to `@PersistenceContext(unitName = "cdbookstorePU", type = PersistenceContextType.TRANSACTION)` which is the default

## Build and Deploy the application on WildFly application server

## Check the new transactional persistence context

* [http://localhost:8080/cdbookstore/faces/admin/CD/search.xhtml]()
* [http://localhost:8080/cdbookstore/faces/admin/musician/view.xhtml?id=-4]()
* [http://localhost:8080/cdbookstore/faces/admin/CD/view.xhtml?id=-13]()
* Displaying and creating CDs from the UI should fail with `failed to lazily initialize a collection of role: org.agoncal.training.javaee6adv.model.CD.musicians`

# KATA - Change all the persistence context from extended to transactional

# DOJO - Get rid of lazy initialization exception by finding by id with relations using LEFT JOIN

## Get rid of lazy initialization exception by finding by id with relations

* CD has a ManyToMany relation with musicians
* `CDBean.findById` can't just use `entityManager.find(CD.class, id)` but instead should use LEFT joins to get relations
* (check the REST endpoints, that's what they do)
* Use a `TypedQuery` (`TypedQuery<CD> findByIdQuery = this.entityManager.createQuery`)
* Remember to set the entity id as a parameter `findByIdQuery.setParameter("entityId", id);`

## Check that the LEFT joins solved the transactional persistence context

* [http://localhost:8080/cdbookstore/faces/admin/CD/search.xhtml]()
* [http://localhost:8080/cdbookstore/faces/admin/musician/view.xhtml?id=-4]()
* [http://localhost:8080/cdbookstore/faces/admin/CD/view.xhtml?id=-13]()

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

## Check the CDBook-Client application is running

* Go to `cdbookclient` directory
* Run `mvn install` which executes the `Main` class

# KATA - Get rid of lazy initialization exception by finding by id with relations using LEFT JOIN

# Restitution

* `LEFT JOIN FETCH`
* Transactional persistence context

# Backup your code

* Save a backup of your code at `../after/05-PersistenceContext/`
