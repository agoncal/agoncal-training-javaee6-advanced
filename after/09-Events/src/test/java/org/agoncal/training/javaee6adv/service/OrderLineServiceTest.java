package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.OrderLine;
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
public class OrderLineServiceTest
{

   @Inject
   private OrderLineService orderlineservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AbstractService.class)
            .addClass(ResourceProducer.class)
            .addClass(OrderLineService.class)
            .addClass(OrderLine.class)
            .addClass(Item.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(orderlineservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = orderlineservice.listAll().size();

      // Creates an object
      OrderLine orderLine = new OrderLine();
      orderLine.setQuantity(99);

      // Inserts the object into the database
      orderLine = orderlineservice.persist(orderLine);
      assertNotNull(orderLine.getId());
      assertEquals(initialSize + 1, orderlineservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      orderLine = orderlineservice.findById(orderLine.getId());
      assertEquals(99, orderLine.getQuantity());

      // Updates the object
      orderLine.setQuantity(99);
      orderLine = orderlineservice.merge(orderLine);

      // Finds the object from the database and checks it has been updated
      orderLine = orderlineservice.findById(orderLine.getId());
      assertEquals(99, orderLine.getQuantity());

      // Deletes the object from the database and checks it's not there anymore
      orderlineservice.remove(orderLine);
      assertEquals(initialSize, orderlineservice.listAll().size());
   }
}
