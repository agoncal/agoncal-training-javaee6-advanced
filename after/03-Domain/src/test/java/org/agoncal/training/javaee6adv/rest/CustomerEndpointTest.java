package org.agoncal.training.javaee6adv.rest;

import org.agoncal.training.javaee6adv.model.Address;
import org.agoncal.training.javaee6adv.model.Customer;
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
public class CustomerEndpointTest
{

   @ArquillianResource
   private URI baseURL;

   @Deployment(testable = false)
   public static WebArchive createDeployment()
   {
      return ShrinkWrap.create(WebArchive.class)
            .addClass(CustomerEndpoint.class)
            .addClass(RestApplication.class)
            .addClass(Customer.class)
            .addClass(Address.class)
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(baseURL).path("rest").path("customers");
      assertEquals(Response.Status.OK.getStatusCode(), target.request(MediaType.APPLICATION_XML).get().getStatus());
   }
}
