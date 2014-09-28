package org.agoncal.training.javaee6adv.client;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Main
{

   private static final String baseURL = "http://localhost:8080/cdbookstore";

   public static void main(String[] args)
   {
      findAllBooks();
      createBook();
      findBookById("1");
      removeBookById("1");
   }

   private static void findAllBooks()
   {
      System.out.println("\n#### findAllBooks");

      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(baseURL).path("rest").path("books");
      System.out.println(target.getUri().toString());

      Response response = target.request(MediaType.APPLICATION_JSON).get();
      System.out.println(response.getStatus());
      System.out.println(response.readEntity(String.class));
   }

   private static void createBook()
   {
      System.out.println("\n#### createBook");

      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(baseURL).path("rest").path("books");
      System.out.println(target.getUri().toString());

      JsonObject json = Json.createObjectBuilder()
            .add("title", "Dummy Title")
            .add("price", "29.99")
            .add("description", "Dummy Description")
            .add("isbn", "1430258489")
            .add("nbOfPages", "240")
            .add("publicationDate", "2013-06-26")
            .build();

      Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(json.toString()));
      System.out.println(response.getStatus());
      System.out.println(response.getLocation());
   }

   private static void findBookById(String id)
   {
      System.out.println("\n#### findBookById");

      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(baseURL).path("rest").path("books").path(id);
      System.out.println(target.getUri().toString());

      Response response = target.request(MediaType.APPLICATION_JSON).get();
      System.out.println(response.getStatus());
      System.out.println(response.readEntity(String.class));
   }

   private static void removeBookById(String id)
   {
      System.out.println("\n#### findBookById");

      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(baseURL).path("rest").path("books").path(id);
      System.out.println(target.getUri().toString());

      Response response = target.request(MediaType.APPLICATION_JSON).delete();
      System.out.println(response.getStatus());
   }
}