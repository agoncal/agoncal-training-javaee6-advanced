package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Address;
import org.agoncal.training.javaee6adv.model.CreditCard;
import org.agoncal.training.javaee6adv.model.CreditCardType;
import org.agoncal.training.javaee6adv.model.Customer;
import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.OrderLine;
import org.agoncal.training.javaee6adv.model.PurchaseOrder;
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
public class PurchaseOrderServiceTest
{

   @Inject
   private PurchaseOrderService purchaseorderservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AbstractService.class)
            .addClass(PurchaseOrderService.class)
            .addClass(PurchaseOrder.class)
            .addClass(Customer.class)
            .addClass(CreditCard.class)
            .addClass(CreditCardType.class)
            .addClass(OrderLine.class)
            .addClass(Address.class)
            .addClass(Item.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(purchaseorderservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = purchaseorderservice.listAll().size();

      // Creates an object
      PurchaseOrder purchaseOrder = new PurchaseOrder();
      purchaseOrder.setCreditCardType(CreditCardType.MASTER_CARD);
      purchaseOrder.setCreditCardExpDate("Dummy");
      purchaseOrder.setCreditCardNumber("Dummy value");
      purchaseOrder.setStreet1("Dummy value");
      purchaseOrder.setCity("Dummy value");
      purchaseOrder.setCountry("Dummy value");
      purchaseOrder.setZipcode("Dummy");

      // Inserts the object into the database
      purchaseOrder = purchaseorderservice.persist(purchaseOrder);
      assertNotNull(purchaseOrder.getId());
      assertEquals(initialSize + 1, purchaseorderservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      purchaseOrder = purchaseorderservice.findById(purchaseOrder.getId());
      assertEquals("Dummy value", purchaseOrder.getCity());

      // Updates the object
      purchaseOrder.setCity("A new value");
      purchaseOrder = purchaseorderservice.merge(purchaseOrder);

      // Finds the object from the database and checks it has been updated
      purchaseOrder = purchaseorderservice.findById(purchaseOrder.getId());
      assertEquals("A new value", purchaseOrder.getCity());

      // Deletes the object from the database and checks it's not there anymore
      purchaseorderservice.remove(purchaseOrder);
      assertEquals(initialSize, purchaseorderservice.listAll().size());
   }
}
