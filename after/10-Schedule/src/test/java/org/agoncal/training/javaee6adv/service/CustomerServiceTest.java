package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Address;
import org.agoncal.training.javaee6adv.model.Customer;
import org.agoncal.training.javaee6adv.util.ResourceProducer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class CustomerServiceTest
{

   @Inject
   private CustomerService customerservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AbstractService.class)
            .addClass(ResourceProducer.class)
            .addClass(CustomerService.class)
            .addClass(Customer.class)
            .addClass(Address.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(customerservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = customerservice.listAll().size();

      // Creates an object
      Customer customer = new Customer();
      customer.setFirstname("Dummy value");
      customer.setLastname("Dummy value");
      customer.setStreet1("Dummy value");
      customer.setCity("Dummy value");
      customer.setZipcode("Dummy");
      customer.setCountry("Dummy value");

      // Inserts the object into the database
      customer = customerservice.persist(customer);
      assertNotNull(customer.getId());
      assertEquals(initialSize + 1, customerservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      customer = customerservice.findById(customer.getId());
      assertEquals("Dummy value", customer.getFirstname());

      // Updates the object
      customer.setFirstname("A new value");
      customer = customerservice.merge(customer);

      // Finds the object from the database and checks it has been updated
      customer = customerservice.findById(customer.getId());
      assertEquals("A new value", customer.getFirstname());

      // Deletes the object from the database and checks it's not there anymore
      customerservice.remove(customer);
      assertEquals(initialSize, customerservice.listAll().size());
   }
}
