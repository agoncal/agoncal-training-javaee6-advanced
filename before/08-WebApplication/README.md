# Java EE 6 Advanced Training - Web Application

In this module you will beautify the CDBook-Store web application and add nice pages and primefaces components to browse CDs and Books (which needs extra service's methods).

# Preparation

## Check

* Check the `generate.fsh` script
* Check the classes to be copied
* Check the `book` directory

## Generate a few artifacts and copy web resources with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Execute the `generate.fsh` script with the command `run ../before/08-WebApplication/generate.fsh` 

## Check the copied the Arquillian tests

* `BookServiceTest` adds a `should_check_images` and a `should_check_books_by_category` method
* `BookBeanTest` adds a `should_check_books_by_category()` test case
* `BookServiceTest` and `CDServiceTest` add a `should_check_images` and a `should_check_books_by_category` method
* `BookBeanTest` and `CDBeanTest` add a `should_check_books_by_category()` test case
* They all `.addAsResource("import.sql", "import.sql")`

# DOJO - Create and test the new service's methods

## Create new methods to the service tier

* `BookService` needs a `public List<String> findAllImages()` method that returns all non null images URL (uses a `TypedQuery<String>`) 
* `BookService` needs a `public List<Book> findByCategory(Long categoryId)` method that returns all the books for a given category
* `CDService` needs a `public List<String> findAllImages()` method that returns all non null images URL (uses a `TypedQuery<String>`) 
* `CDService` needs a `public List<CD> findByGenre(Long genreId)` method that returns all the CDs for a given genre

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# KATA - Create and test the new service's methods


# DOJO - Setup a new UI

## Webjars is a new way to add Bootstrap

* Delete Bootstrap because it's used with Webjars now `rm src/main/webapp/resources/bootstrap.css`
* The `org.webjars.bootstrap` jar in our depedencies (`pom.xml`) brings the needed style sheet

##  Check the copied book pages

* The `book/index.xhtml` page uses the `bookService.findAllImages()` method
* The `book/navigation.xhtml` page uses the `categoryService.listAll()` method
* The `book/viewPerCategory.xhtml` needs a `bookBean.findByCategory` method
* The pages use a few Prime Faces components : `imageSwitch` and `dataScroller` 
* Remember that the `@Named` annotation allows a service to be used within a JSF page

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
* The top link `Book` should be enabled (but not `CD` yet)
* [http://localhost:8080/cdbookstore/faces/book/index.xhtml]() give you access to the book pages

# KATA - Create web pages to browse CDs

## Create the JSF pages

* Based on `src/main/webapp/book` create a set of pages to browse the CDs
* The `cd/index.xhtml` page needs a `cdService.findAllImages()` method
* The `cd/navigation.xhtml` page display all the genres and needs a `genreService.listAll()` method
* The `cd/view.xhtml` page displays the information for a book
* The `viewPerGenre.xhtml` page displays the list of CDs per genre and needs a `cdBean.findByGenre` method

## Build, Deploy and check the web application
                 
* With a browser go to [http://localhost:8080/cdbookstore]()
* The top link `Book` should be enabled 
* [http://localhost:8080/cdbookstore/faces/book/index.xhtml]() give you access to the book pages

## Check JSF debug information

* Press `CTRL-SHIFT-D` thanks to `javax.faces.PROJECT_STAGE` in `web.xml`

## Check the CDBook-Client application is running

* Go to `cdbookclient` directory
* Run `mvn install` which executes the `Main` class

# Restitution

* Bootstrap as a JSF resource (jar in a classpath)
* JSF staging (`projectStage`)
* In Arquillian we can add an `import.sql` as a resource
* EJB stateless can be `@Named` if they are used in a page (not all our EJBs are `@Named`)

# Backup your code

* Save a backup of your code at `../after/08-WebApplication/`
