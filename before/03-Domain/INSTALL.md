# Java EE 6 Advanced Training - Domain

JBoss Forge doesn't know how to generate inheritance or Embeddable. In this module you will refactor the domain model to follow JPA best practices introducing inheritance and embeddables.

# DOJO - Refactor Book and CD to inherit from Item

## Refactor Book and CD to inherit from Item

* `Item` should define the inheritance `@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)`
* `Item` attributes should be `protected` instead of `private`
* `Book` and `CD` should `extends Item`
* `Book` and `CD` should get rid of the duplicate attributes, getters and setters : `id`, `version`, `title`, `price`, `description` and `imageURL` ;

## Refactor and execute the tests in a remote environment

* Add `.addClass(Item.class)` to the Arquillian test `BookEndpointTest`, `CDEndpointTest`, `BookBeanTest` and `CDBeanTest`
* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# DOJO - Address and CreditCard should be embeddables

## Address and CreditCard should be embeddables

* `Address` and `CreditCard` should be `@Embeddable` instead of an `@Entity` (get rid of `id` and `version`, its getters and `hashcode`)
* A `Customer` has an `@Embedded` home address that needs to be `@Valid` (`@Embedded @Valid private Address homeAddress = new Address();`)
* A `PurchaseOrder` has an `@Embedded` delivery address that needs to be `@Valid` (`@Embedded @Valid private Address deliveryAddress = new Address();`)
* A `PurchaseOrder` has an `@Embedded` credit card that needs to be `@Valid` (`@Embedded @Valid private CreditCard creditCard = new CreditCard();`)
* `Customer` and `PurchaseOrder` should get rid of the duplicate attributes of `Address` : `street1`, `street2`, `city`, `state` ,`zipcode` and `country` 
* `PurchaseOrder` should get rid of the duplicate attributes of `CreditCard` : `creditCardNumber`, `creditCardType` and `creditCardExpDate` 
* Refactor getters `public String getStreet1() { return homeAddress.getStreet1(); }`
* Refactor setters `public void setState(String state) { this.homeAddress.setState(state); }`
* Refactory `toString` `if (homeAddress.getStreet1() != null && !homeAddress.getStreet1().trim().isEmpty()) result += ", street1: " + homeAddress.getStreet1();`

## Refactor and execute the tests in a remote environment

* Add `.addClass(Address.class)` to the Arquillian test `CustomerEndpointTest`, `CustomerBeanTest`  and `PurchaseOrderBeanTest`
* Add `.addClass(CreditCard.class)` to the Arquillian test `PurchaseOrderBeanTest`
* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

## Build the application

* Use Maven and build the application with `mvn clean install`

## Deploy the application on WildFly application server

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* Go to the [admin console](http://localhost:9990/)
* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_

## Check the web application

* With a browser go to [http://localhost:8080/cdbookstore]()
* Thanks to inheritance, now the Item page shows both Books and CDs

## Check the REST interfaces

* The following REST interfaces are defined
* [http://localhost:8080/cdbookstore/rest/authors]()
* [http://localhost:8080/cdbookstore/rest/books]()
* [http://localhost:8080/cdbookstore/rest/cds]()
* [http://localhost:8080/cdbookstore/rest/customers]()
* [http://localhost:8080/cdbookstore/rest/musicians]()
* [http://localhost:8080/cdbookstore/rest/purchaseorders]()
* Use [PostMan REST client](https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm) to create/update/remove entities

## Backup your code

* Save a backup of your code at `../after/03-Domain/`
