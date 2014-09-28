# Java EE 6 Advanced Training - Event

In this module you will add the capability of buying a book or a CD. The idea is to have a _Buy_ button on the Book and CD page, and by pressing it, it will send a CDI event. The observer of this event will then create (persist in the database) a purchase order.

# DOJO - Add buying capabilities to Books
 
## Add a Buy button on the Book page
 
* The Book web page has a _Buy_ button and it displays an information message that the book has been successfully bought
* Replace the `view.xhtml` page of the Book
* `cp ../before/09-Events/view.xhtml src/main/webapp/book`

## Add a buy method on BookBean 

* The `BookBean` should have a new `public String buy()` method that returns null
* The method finds the book that we just bought
* It displays a JSF message (use the JSF `context` to send a message, you get the context with `FacesContext context = FacesContext.getCurrentInstance()`)
* It then fires an event

## Build, Deploy and check the web application
                 
* With a browser go to [http://localhost:8080/cdbookstore]()
* View a Book (for example [http://localhost:8080/cdbookstore/faces/book/view.xhtml?id=-12]())
* Click on the _Buy_ button, the message should be displayed

# KATA - Add buying capabilities to CDs

# DOJO - Add a listener that creates a purchase order

## The purchase order service creates a purchase order

* Copy the `RandomService` class, it will randomly generate data
* `cp ../before/09-Events/RandomService.java src/main/java/org/agoncal/training/javaee6adv/service`
* In `PurchaseOrderService` add a `public void itemHasBeenBought(Item itemBought)` method
* It should create a `purchaseOrder` (with a random credit card, a random address, a random customer) and the detached `itemBought`
* Use `getEntityManager().persist(purchaseOrder)` to persist the `purchaseOrder`
* You might have to change the cascade and fetch on the `orderLines` attributes (`@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)`)
* Make sure the method listens to the event

## Build, Deploy and check the web application
                 
* With a browser go to [http://localhost:8080/cdbookstore]()
* View a Book (for example [http://localhost:8080/cdbookstore/faces/book/view.xhtml?id=-12]())
* Click on the _Buy_ button, the message should be displayed
* Go to the Administration page ([http://localhost:8080/cdbookstore/faces/admin/purchaseOrder/search.xhtml]()) and check the purchase ordre is created

## Refactor the Purchase Order tests

* The `PurchaseOrderServiceTest` and `PurchaseOrderBeanTest` need to be refactor

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# Backup your code

* Save a backup of your code at `../after/09-Events/`
