package org.agoncal.training.javaee6adv.rest;

import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.model.Book;
import org.agoncal.training.javaee6adv.model.Category;
import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.Language;
import org.agoncal.training.javaee6adv.model.Publisher;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@RunAsClient
public class BookEndpointTest
{

   @ArquillianResource
   private URI baseURL;

   @Deployment(testable = false)
   public static WebArchive createDeployment()
   {
      return ShrinkWrap.create(WebArchive.class)
            .addClass(BookEndpoint.class)
            .addClass(RestApplication.class)
            .addClass(Book.class)
            .addClass(Item.class)
            .addClass(Language.class)
            .addClass(Category.class)
            .addClass(Author.class)
            .addClass(Publisher.class)
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(baseURL).path("rest").path("books");
      assertEquals(Response.Status.OK.getStatusCode(), target.request(MediaType.APPLICATION_XML).get().getStatus());
   }

   @Test
   public void should_produce_json()
   {
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(baseURL).path("rest").path("books");
      assertEquals(Response.Status.OK.getStatusCode(), target.request(MediaType.APPLICATION_JSON).get().getStatus());
   }
}
