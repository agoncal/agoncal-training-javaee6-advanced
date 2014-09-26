# Java EE 6 Advanced Training - REST Client

In this module you will use the JAX-RS 2.0 Client API to invoke programmatically the CDBook-Store REST endpoints. For that you will create the new project CDBook-Client

# DOJO - Test the JSon support of REST Endpoints

## Add a JSON test to the Author Endpoint

* Arquillian tests only test XML
* Add a new test `should_produce_json` method in `AuthorEndpointTest` that checks the GET method produces JSon

   @Test
   public void should_produce_json()
   {
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(baseURL).path("rest").path("authors");
      assertEquals(Response.Status.OK.getStatusCode(), target.request(MediaType.APPLICATION_JSON).get().getStatus());
   }

## Execute the AuthorEndpointTest in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

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

## Check the REST interfaces with JSon

* [http://localhost:8080/cdbookstore/rest/authors]()
* Use [PostMan REST client](https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm) to create/update/remove entities

# KATA - Test the JSon support of all REST Endpoints

* Add a new test `should_produce_json` method in `BookEndpointTest`, `CDEndpointTest`, `CustomerEndpointTest` and `MusicianEndpointTest` that checks the GET method produces JSon

## Execute the all REST Endpoint test in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# DOJO - The CDBook-Client application consumes the REST endpoints

## Generate the new project CDBook-Client with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Execute the `generate.fsh` script with the command `run ../before/04-RestClient/generate.fsh` 
* Go to the `cdbookclient` directory

## Add a Maven exec plugin

* In order to execute the generated `Main` class with Maven, add the following to the `pom.xml` inside the `<build>` element

    <plugins>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.3.2</version>
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
    
* Run `mvn install` and make sure the `Main` class is executed and the `Hi there!` message is displayed
    
## Invoke the CD-Book Store REST Services with the Main class

* Remember that our REST endpoints tests use the Client API (get inspiration from them)

### Get all the books
    
* In the `Main` create a method that returns all the Books in JSON
* Method `private static void findAllBooks()`
* `Response response = target.request(MediaType.APPLICATION_JSON).get();`

# KATA - Add extra methods to the CDBook-Client

### Create a book

* In the `Main` create a method that creates one book given some JSON
* Method `private static void createBook()`
* Create this JSon String with `Json.createObjectBuilder()` = `String json = "{\"title\":\"Dummy Title\",\"price\":29.99,\"description\":\"Dummy Description\",\"isbn\":\"1430258489\",\"nbOfPages\":240,\"publicationDate\":\"2013-06-26\"}";`
* `Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(json.toString()));`
* Display the location `System.out.println(response.getLocation());`

### Find a book given an id

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