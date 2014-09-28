package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Publisher;
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
public class PublisherServiceTest
{

   @Inject
   private PublisherService publisherservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AbstractService.class)
            .addClass(PublisherService.class)
            .addClass(Publisher.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(publisherservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = publisherservice.listAll().size();

      // Creates an object
      Publisher publisher = new Publisher();
      publisher.setName("Dummy value");

      // Inserts the object into the database
      publisher = publisherservice.persist(publisher);
      assertNotNull(publisher.getId());
      assertEquals(initialSize + 1, publisherservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      publisher = publisherservice.findById(publisher.getId());
      assertEquals("Dummy value", publisher.getName());

      // Updates the object
      publisher.setName("A new value");
      publisher = publisherservice.merge(publisher);

      // Finds the object from the database and checks it has been updated
      publisher = publisherservice.findById(publisher.getId());
      assertEquals("A new value", publisher.getName());

      // Deletes the object from the database and checks it's not there anymore
      publisherservice.remove(publisher);
      assertEquals(initialSize, publisherservice.listAll().size());
   }
}
