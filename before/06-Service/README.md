# Java EE 6 Advanced Training - Service Tier

Both JSF Backing Beans and REST Endpoints use the `EntityManager`. In this module, you will add a extra service layer and make sure JSF and REST use it.

# DOJO - Generate and test service tier with JBoss Forge

## Generate the service tier with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Execute the `generate.fsh` script with the command `run ../before/06-Service/generate.fsh` 

## Execute the tests in a remote environment to check the new tests are running

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

## Write the AuthorService

* Check the classes `AbstractService.java`, `MusicianService.java` and `PublisherService.java`
* Code the `AuthorService` following the same logic
* Services should extend `AbstractService` (which uses the `EntityManager`) 
* Services constructor should call super passing the type (eg. `public AuthorService() { super(Author.class); }`) 
* Each service overrides the `getSearchPredicates` method that has the signature `protected Predicate[] getSearchPredicates(Root<Author> root, Author example)`. Copy the content from the JSF Backing bean

## Write the AuthorServiceTest

* Check the classes `MusicianServiceTest.java` and `PublisherServiceTest.java`
* Code the `AuthorServiceTest` following the same logic until the tests passes
* Packaging should add `.addClass(AbstractService.class)`
* Each test case should implement a `should_crud` method

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# KATA - Generate the service tier with JBoss Forge, produce the EntityManager and test the service layer

## Write the other services

* Check the classes `AbstractService.java`, `MusicianService.java` and `PublisherService.java`
* Code the `AuthorService`, `BookService`, `CategoryService`, `CDService`, `CustomerService`, `GenreService`, `ItemService`, `MajorLabelService` and `OrderLineService` following the same logic
* Services should extend `AbstractService` (which uses the produced `EntityManager`) 
* Services constructor should call super passing the type (eg. `public AuthorService() { super(Author.class); }`) 
* Each service overrides the `getSearchPredicates` method that has the signature `protected Predicate[] getSearchPredicates(Root<Author> root, Author example)`. Copy the content from the JSF Backing bean
* Due to transactional persistence context :
* Add a `public Book findByIdWithRelations(Long id)` in the book service that returns a book with its relations
* Add a `public CD findByIdWithRelations(Long id)` in the CD service that returns a book with its relations
* Add a `public List<Book> listAllWithRelations(Integer startPosition, Integer maxResult)` in the book service that returns a list of books with its relations
* Add a `public List<CD> listAllWithRelations(Integer startPosition, Integer maxResult)`in the CD service that returns a list of books with its relations

## Write the other service tests

* Check the classes `MusicianServiceTest.java` and `PublisherServiceTest.java`
* Code the `AuthorServiceTest`, `BookServiceTest`, `CategoryServiceTest`, `CDServiceTest`, `CustomerServiceTest`, `GenreServiceTest`, `ItemServiceTest`, `MajorLabelServiceTest` and `OrderLineServiceTest` following the same logic until the tests passes
* Packaging should add `.addClass(AbstractService.class)`
* Each test case should implement a `should_crud` method

# DOJO - Refactor the AuthorBean and AuthorBeanTest

## Refactor the AuthorBean

* In `AuthorBean` get rid of `EntityManager` and instead inject `AuthorService` (eg. `@Inject private AuthorService service;`)
* Each call to the `entityManager` variable has to be replaced by a call to the service (eg. `entityManager.find` replaced by `service.findById`). So you can replace all `this.entityManager` with `this.service`
* Why can we get rid of the `entityManager.flush` call ?
* Methods `getSearchPredicates` can be removed as they have been moved to the services
* To `paginate` methods use `this.service.count(example)` and `this.service.page(example, page, getPageSize())`
* Methods `getAll` should just `return this.service.listAll()`
* Remove `SessionContext` (why do we have `sessionContext.getBusinessObject(AuthorBean.class)`)
* Remove `ejbProxy` and replace `ejbProxy.findById` by `service.findById`  
* `AuthorBean` doesn't need to be transactional now (get rid of `@Stateful`)

## Refactor the AuthorBeanTest

* The `AuthorBeanTest` needs to add service dependencies in the packaging (eg. `AbstractService`, `AuthorService`)

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# KATA - Refactor all the JSF backing beans and tests

## Refactor the JSF backing beans

* In JSF backing beans get rid of `EntityManager` and instead inject the appropriate service (eg. `@Inject private AuthorService service;`)
* Each call to the `entityManager` variable has to be replaced by a call to the service (eg. `entityManager.find` replaced by `service.findById`). So you can replace all `this.entityManager` with `this.service`
* `CDBean` and `BookBean` `findById` method should `return this.service.findByIdWithRelations(id)` to get all the relations
* Why can we get rid of the `entityManager.flush` call ?
* Methods `getSearchPredicates` can be removed as they have been moved to the services
* To `paginate` methods use `this.service.count(example)` and `this.service.page(example, page, getPageSize())`
* Methods `getAll` should just `return this.service.listAll()`
* Remove `SessionContext` (why do we have `sessionContext.getBusinessObject(AuthorBean.class)`)
* Remove `ejbProxy` and replace `ejbProxy.findById` by `service.findById`  
* Backing beans don't need to be transactional now (get rid of `@Stateful`)

## Refactor the JSF backing bean tests

* JSF backing beans test need now to add service dependencies in the packaging (eg. `AbstractService`, `AuthorService`)

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# DOJO - Refactor the REST endpoints and tests

## Refactor the AuthorEndpoint

* In `AuthorEndpoint` get rid of `EntityManager` and instead inject `AuthorService` (eg. `@Inject private AuthorService service;`)
* Each call to the `em` variable has to be replaced by a call to the service (eg. `em.find` replaced by `service.findById`). So you can replace all `em.` with `service.`
* `listAll` methods should `return service.listAll(startPosition, maxResult)`  
* `AuthorEndpoint` doesn't need to be transactional now (get rid of `@Stateless`)

## Refactor the existing tests

* `AuthorEndpointTest` needs now to add service dependencies in the packaging (eg. `AbstractService`, `AuthorService`)

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# KATA - Refactor all the REST endpoints and tests

## Refactor the REST endpoints

* In REST enpoints get rid of `EntityManager` and instead inject the appropriate service (eg. `@Inject private AuthorService service;`)
* Each call to the `em` variable has to be replaced by a call to the service (eg. `em.find` replaced by `service.findById`). So you can replace all `em.` with `service.`
* `listAll` methods should `return service.listAll(startPosition, maxResult)`  
* `CDEndpoint` and `BookEndpoint` `findById` method should use `Book entity = service.findByIdWithRelations(id)` to get all the relations
* `CDEndpoint` and `BookEndpoint` `listAll` method should `return service.listAllWithRelations(startPosition, maxResult);`
* REST endpoints don't need to be transactional now (get rid of `@Stateless`)

## Refactor the existing tests

* REST endpoint tests need now to add service dependencies in the packaging (eg. `AbstractService`, `AuthorService`)

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

## Build the application

* Use Maven and build the application with `mvn clean install`

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

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()

## Check the CDBook-Client application is running

* Go to `cdbookclient` directory
* Run `mvn install` which executes the `Main` class

# Restitution

* EJBs have as many methods as needed depending on the relations (LEFT JOINs)
* JSF backing beans & REST endpoints do not have to be transactional
* EJBs are transactional by default
* Flush in REST endpoints is not needed
* The application now uses transactional persistence context and JSF Beans use conversations 

# Backup your code

* Save a backup of your code at `../after/06-Service/`
