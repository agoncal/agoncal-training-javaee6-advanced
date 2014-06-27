# Java EE 6 Advanced Training - Web Application

In this module you will beautify the CDBook-Store web application and add nice pages and primefaces components to browse CDs and Books.

## Generating a few artifacts

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Execute the `generate.fsh` script with the command `run ../before/07-WebApplication/generate.fsh` 

# DOJO - Setup a new UI

## Add webjars to get rid of boostrap

* Delete Bootstrap because it's used with Webjars now `rm src/main/webapp/resources/bootstrap.css`
* The `org.webjars.bootstrap` jar brings the needed style sheet

###  Copy the templates, css and logos
 
* `cp ../before/07-WebApplication/mainTemplate.xhtml src/main/webapp/resources/scaffold/`
* `cp ../before/07-WebApplication/pageTemplate.xhtml src/main/webapp/resources/scaffold/`
* `cp ../before/07-WebApplication/banner.png src/main/webapp/resources/`
* `cp ../before/07-WebApplication/forge-logo.png src/main/webapp/resources/`
* `cp ../before/07-WebApplication/forge-style.css src/main/webapp/resources/`
* `cp ../before/07-WebApplication/index.html src/main/webapp/`
* `cp ../before/07-WebApplication/index.xhtml src/main/webapp/`

## Build, Deploy and check the web application
                 
* With a browser go to [http://localhost:8080/cdbookstore]()
* The top links are disabled except `Adminstration`
* [http://localhost:8080/cdbookstore/faces/admin/index.xhtml]() give you access to the admin pages

# DOJO - Web pages to browse Books

##  Copy the book pages

* `cp ../before/07-WebApplication/book src/main/webapp/`
* The `index.xhtml` page needs a `bookService.findAllImages()` method
* The `navigation.xhtml` page needs a `categoryService.listAll()` method
* The `viewPerCategory.xhtml` needs a `bookBean.findByCategory` method
* The pages use a few Prime Faces components : `imageSwitch` and `dataScroller` 

## Copy the Arquillian tests

* `cp ../before/07-WebApplication/BookServiceTest.java src/test/java/org/agoncal/training/javaee6adv/service`
* It adds a `should_check_images` and a `should_check_books_by_category` method
* `cp ../before/07-WebApplication/BookBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view`
* It adds a `should_check_books_by_category()` test case

## Refactor the JSF backing bean and EJB

* `BookService` has a `public List<String> findAllImages()` method that returns all non null images URL (uses a `TypedQuery<String>`) 
* `BookService` has a `public List<Book> findByCategory(Long categoryId)` method that returns all the books for a given category
* `BookBean` should have a `private Long categoryId` and a `private List<Book> booksPerCateogry` with getters/setters 
* `BookBean` has a `public void findByCategory()` method that calls the EJB
* Remember that the `@Named` annotation allows a service to be used within a JSF page

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

## Build, Deploy and check the web application
                 
* With a browser go to [http://localhost:8080/cdbookstore]()
* The top link `Book` should be enabled 
* [http://localhost:8080/cdbookstore/faces/book/index.xhtml]() give you access to the book pages

# DOJO - Web pages to browse CDs

## Create the JSF pages

* Based on `webapp/book` create a set of pages to browse the CDs
* The `webapp/cd/index.xhtml` page needs a `cdService.findAllImages()` method
* The `webapp/cd/navigation.xhtml` page display all the genres and needs a `genreService.listAll()` method
* The `webapp/cd/view.xhtml` page displays the information for a book
* The `viewPerGenre.xhtml` page displays the list of CDs per genre and needs a `cdBean.findByGenre` method
* Get inspiration from `src/main/webapp/book`

## Copy the Arquillian tests

* `cp ../before/07-WebApplication/CDServiceTest.java src/test/java/org/agoncal/training/javaee6adv/service`
* It adds a `should_check_images` and a `should_check_books_by_genre` method
* `cp ../before/07-WebApplication/CDBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view`
* It adds a `should_check_books_by_genre()` test case

## Refactor the JSF backing bean and EJB

* `CDService` has a `public List<String> findAllImages()` method that returns all non null images URL (uses a `TypedQuery<String>`) 
* `CDService` has a `public List<CD> findByGenre(Long genreId)` method that returns all the CDs for a given genre
* `CDBean` should have a `private Long genreId` and a `private List<CD> cdsPerGenre` with getters/setters 
* `CDBean` has a `public void findByGenre()` method that calls the EJB
* Remember that the `@Named` annotation allows a service to be used within a JSF page

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

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

* Save a backup of your code at `../after/07-WebApplication/`
