package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.Address;
import org.agoncal.training.javaee6adv.model.CreditCard;
import org.agoncal.training.javaee6adv.model.CreditCardType;
import org.agoncal.training.javaee6adv.model.Customer;
import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.OrderLine;
import org.agoncal.training.javaee6adv.model.Person;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class PurchaseOrderBeanTest
{

   @Inject
   private PurchaseOrderBean purchaseorderbean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(PurchaseOrderBean.class)
            .addClass(PurchaseOrder.class)
            .addClass(Person.class)
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
      Assert.assertNotNull(purchaseorderbean);
   }

   @Test
   public void should_crud()
   {
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
      purchaseorderbean.setPurchaseOrder(purchaseOrder);
      purchaseorderbean.create();
      purchaseorderbean.update();
      purchaseOrder = purchaseorderbean.getPurchaseOrder();
      assertNotNull(purchaseOrder.getId());

      // Finds the object from the database and checks it's the right one
      purchaseOrder = purchaseorderbean.findById(purchaseOrder.getId());
      assertEquals("Dummy value", purchaseOrder.getCountry());

      // Deletes the object from the database and checks it's not there anymore
      purchaseorderbean.setId(purchaseOrder.getId());
      purchaseorderbean.create();
      purchaseorderbean.delete();
      purchaseOrder = purchaseorderbean.findById(purchaseOrder.getId());
      assertNull(purchaseOrder);
   }

   @Test
   public void should_paginate()
   {
      // Creates an empty example
      PurchaseOrder example = new PurchaseOrder();

      // Paginates through the example
      purchaseorderbean.setExample(example);
      purchaseorderbean.paginate();
      assertTrue((purchaseorderbean.getPageItems().size() == purchaseorderbean.getPageSize()) || (purchaseorderbean.getPageItems().size() == purchaseorderbean.getCount()));
   }
}
