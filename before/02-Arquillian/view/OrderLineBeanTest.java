package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.OrderLine;
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
public class OrderLineBeanTest
{

   @Inject
   private OrderLineBean orderlinebean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(OrderLineBean.class)
            .addClass(OrderLine.class)
            .addClass(Item.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(orderlinebean);
   }

   @Test
   public void should_crud()
   {
      // Creates an object
      OrderLine orderLine = new OrderLine();
      orderLine.setQuantity(99);

      // Inserts the object into the database
      orderlinebean.setOrderLine(orderLine);
      orderlinebean.create();
      orderlinebean.update();
      orderLine = orderlinebean.getOrderLine();
      assertNotNull(orderLine.getId());

      // Finds the object from the database and checks it's the right one
      orderLine = orderlinebean.findById(orderLine.getId());
      assertEquals(99, orderLine.getQuantity());

      // Deletes the object from the database and checks it's not there anymore
      orderlinebean.setId(orderLine.getId());
      orderlinebean.create();
      orderlinebean.delete();
      orderLine = orderlinebean.findById(orderLine.getId());
      assertNull(orderLine);
   }

   @Test
   public void should_paginate()
   {
      // Creates an empty example
      OrderLine example = new OrderLine();

      // Paginates through the example
      orderlinebean.setExample(example);
      orderlinebean.paginate();
      assertTrue((orderlinebean.getPageItems().size() == orderlinebean.getPageSize()) || (orderlinebean.getPageItems().size() == orderlinebean.getCount()));
   }
}
