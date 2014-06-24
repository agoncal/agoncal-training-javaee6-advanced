package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.CD;
import org.agoncal.training.javaee6adv.model.Genre;
import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.MajorLabel;
import org.agoncal.training.javaee6adv.model.Musician;
import org.agoncal.training.javaee6adv.service.CDService;
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
public class CDServiceTest
{

   @Inject
   private CDService cdservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(Resources.class)
            .addClass(AbstractService.class)
            .addClass(CDService.class)
            .addClass(CD.class)
            .addClass(Item.class)
            .addClass(MajorLabel.class)
            .addClass(Genre.class)
            .addClass(Musician.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(cdservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = cdservice.listAll().size();

      // Creates an object
      CD cd = new CD();
      cd.setTitle("Dummy value");

      // Inserts the object into the database
      cd = cdservice.persist(cd);
      assertNotNull(cd.getId());
      assertEquals(initialSize+1, cdservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      cd = cdservice.findById(cd.getId());
      assertEquals("Dummy value", cd.getTitle());

      // Updates the object
      cd.setTitle("A new value");
      cd = cdservice.merge(cd);

      // Finds the object from the database and checks it has been updated
      cd = cdservice.findById(cd.getId());
      assertEquals("A new value", cd.getTitle());

      // Deletes the object from the database and checks it's not there anymore
      cdservice.remove(cd);
      assertEquals(initialSize, cdservice.listAll().size());
   }
}
