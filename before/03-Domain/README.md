# Java EE 6 Advanced Training - Domain

JBoss Forge doesn't know how to generate inheritance or embed embeddable. In this module you will refactor the domain model to follow JPA best practices introducing inheritance and embeddeds.

# Preparation

## Check

* The DDLs are different from the first module

# DOJO - Refactor Book and CD to inherit from Item

## Refactor Book and CD to inherit from Item

* `Item` should define the inheritance `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`
* `Item` should override the default discriminator column `dtype` : `@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.CHAR)`
* `Item` discriminator column is `@DiscriminatorValue("I")`
* `Item` attributes should be `protected` instead of `private` (so they can be used easily in inherted classes `Book` and `CD`)
* `Book` and `CD` should `extends Item`
* `Book` and `CD` should get rid of the duplicate attributes, getters and setters : `id`, `version`, `title`, `price`, `description` and `imageURL` ;
* `Book` discriminator column is `@DiscriminatorValue("B")`
* `CD` discriminator column is `@DiscriminatorValue("C")`

## Refactor and execute the tests in a remote environment

* Add `.addClass(Item.class)` to the Arquillian test `BookEndpointTest`, `CDEndpointTest`, `BookBeanTest` and `CDBeanTest`
* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# KATA - Refactor Book and CD to inherit from Item and pass tests

# DOJO - CreditCard should be embedded

## CreditCard should be embedded

* `CreditCard` is an `@Embeddable` not an `@Entity`
* A `PurchaseOrder` has an `@Embedded` credit card that needs to be `@Valid` (`@Embedded @Valid private CreditCard creditCard = new CreditCard();`)
* In `PurchaseOrder` get rid of the duplicate attributes of `CreditCard` : `creditCardNumber`, `creditCardType` and `creditCardExpDate` 
* Refactor getters `public String getCreditCardNumber() { return creditCard.getCreditCardNumber(); }`
* Refactor setters `public void setCreditCardNumber(String creditCardNumber) { this.creditCard.setCreditCardNumber(creditCardNumber); }`
* Refactor `toString`

## Refactor and execute the tests in a remote environment

* Add `.addClass(CreditCard.class)` to the Arquillian test `PurchaseOrderBeanTest`
* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# KATA - CreditCard and Address should be embedded and pass tests

* `Address` is an `@Embeddable` not `@Entity`
* A `Customer` has an `@Embedded` home address that needs to be `@Valid` (`@Embedded @Valid private Address homeAddress = new Address();`)
* A `PurchaseOrder` has an `@Embedded` delivery address that needs to be `@Valid` (`@Embedded @Valid private Address deliveryAddress = new Address();`)
* `Customer` and `PurchaseOrder` should get rid of the duplicate attributes of `Address` : `street1`, `street2`, `city`, `state` ,`zipcode` and `country` 
* `Customer` and `PurchaseOrder` should get rid of the duplicate getter/setter of `Address` : `getStreet1`, `setStreet1`... 
* Refactor getters `public String getStreet1() { return homeAddress.getStreet1(); }`
* Refactor setters `public void setState(String state) { this.homeAddress.setState(state); }`
* Refactor `toString` `if (homeAddress.getStreet1() != null && !homeAddress.getStreet1().trim().isEmpty()) result += ", street1: " + homeAddress.getStreet1();`

## Refactor and execute the tests in a remote environment

* Add `.addClass(Address.class)` to the Arquillian test `CustomerEndpointTest`, `CustomerBeanTest`  and `PurchaseOrderBeanTest`
* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

## Then add data to the database
 
* Copy the file `import.sql` to `src/main/resources`
* `cp ../before/03-Domain/import.sql src/main/resources`

# Build and run the application

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
* Browse pages to create/update/remove entities (thanks to `import.sql` you should have data)
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

# Restitution

* `@Inheritance` and discriminator
* `@Embeddable` and `@Embedded`
* `import.sql` automatic loading

# Backup your code

* Save a backup of your code at `../after/03-Domain/`
