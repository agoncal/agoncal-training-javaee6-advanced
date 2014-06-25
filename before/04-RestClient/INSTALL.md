# Java EE 6 Advanced Training - REST Client

In this module you will use the JAX-RS 2.0 Client API to invoke programmatically the CDBook-Store REST endpoints. For that you will create the new project CDBook-Client

# DOJO - Add JSON support in CDBook-Store Endpoints
 
## Add JSON support in CDBook-Store Endpoints

* In the REST endpoints :
* Replace all the `@Consumes("application/xml")` with `@Consumes({"application/xml","application/json"})`
* Replace all the `@Produces("application/xml")` with `@Produces({"application/xml","application/json"})`

## Deploy the CDBook-Store application on WildFly

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* Make sure WildFly has enough memory `-Xms64m -Xmx1024m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true`
* Go to the [admin console](http://localhost:9990/)
* Deploy the `cdbookstore/target/cdbookstore.war` file in _Runtime -> Manage Deployments -> Add -> Enable_

## Check the REST interfaces with PostMan

* Install Postman REST Client from the Chrome store 
* Do CRUD operations on the REST interfaces using Postman REST Client
 
# DOJO - The CDBook-Client application consumes the REST endpoints

## Generate the new project CDBook-Client with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Execute the `generate.fsh` script with the command `run ../before/01-Forge/generate.fsh` 
* Go to the `cdbookclient` directory

## Add a Maven exec plugin

* In order to execute the generated `Main` class with Maven, add the following to the `pom.xml`

    <plugins>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.3.1</version>
        <executions>
          <execution>
            <id>rest-client</id>
            <phase>install</phase>
            <goals>
              <goal>java</goal>
            </goals>
            <configuration>
              <mainClass>org.agoncal.training.javaee6adv.client.Main</mainClass>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
    
* Run `mvn install` and make sure the `Main` class is executed
    
## Invoke the CD-Book Store REST Services with the Main class

### Get all the books
    
* In the `Main` create a method that returns all the Books in JSON
* Method `private static void findAllBooks()`
* `Response response = target.request(MediaType.APPLICATION_JSON).get();`

### Create a book

* In the `Main` create a method that creates one book given some JSON
* Method `private static void createBook()`
* Create this JSon String with `Json.createObjectBuilder()` = `String json = "{\"title\":\"Dummy Title\",\"price\":29.99,\"description\":\"Dummy Description\",\"isbn\":\"1430258489\",\"nbOfPages\":240,\"publicationDate\":\"2013-06-26\"}";`
* `Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(json.toString()));`
* Display the location `System.out.println(response.getLocation());`

## Find a book given an id

* In the `Main` create a method that returns a book in JSON given an id
* Method `private static void findBookById(String id)`

### Remove a book given an id

* In the `Main` create a method that creates one book given some JSON
* Method `private static void removeBookById(String id)`
* Display the status `System.out.println(response.getLocation());`

## Execute the CDBook-Client application

* While CDBook-Store is up and running, run `mvn install` and make sure the `Main` class is executed

# Restitution

* JAX-RS 2.0 Client API : `ClientBuilder`, `Client`, `WebTarget`, `Response`
* JSON-P : `Json` Façade, `JSonObject`
* Using the Client API in Arquillian (using JAX-RS 2.0 in a Java EE 6 application)