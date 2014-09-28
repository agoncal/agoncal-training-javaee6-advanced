package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.service.ItemService;
import javax.inject.Inject;

import org.agoncal.training.javaee6adv.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@RunWith(Arquillian.class)
public class ItemServiceTest
{

   @Inject
   private ItemService itemservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(Resources.class)
            .addClass(AbstractService.class)
            .addClass(ItemService.class)
            .addClass(Item.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(itemservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = itemservice.listAll().size();

      // Creates an object
      Item item = new Item();
      item.setTitle("Dummy value");

      // Inserts the object into the database
      item = itemservice.persist(item);
      assertNotNull(item.getId());
      assertEquals(initialSize+1, itemservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      item = itemservice.findById(item.getId());
      assertEquals("Dummy value", item.getTitle());

      // Updates the object
      item.setTitle("A new value");
      item = itemservice.merge(item);

      // Finds the object from the database and checks it has been updated
      item = itemservice.findById(item.getId());
      assertEquals("A new value", item.getTitle());

      // Deletes the object from the database and checks it's not there anymore
      itemservice.remove(item);
      assertEquals(initialSize, itemservice.listAll().size());
   }
}
