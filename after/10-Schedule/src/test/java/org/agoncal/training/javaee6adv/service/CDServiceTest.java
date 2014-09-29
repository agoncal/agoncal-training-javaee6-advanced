package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.CD;
import org.agoncal.training.javaee6adv.model.Genre;
import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.MajorLabel;
import org.agoncal.training.javaee6adv.model.Musician;
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
public class CDServiceTest
{

   @Inject
   private CDService cdservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AbstractService.class)
            .addClass(ResourceProducer.class)
            .addClass(CDService.class)
            .addClass(CD.class)
            .addClass(Item.class)
            .addClass(MajorLabel.class)
            .addClass(Genre.class)
            .addClass(Musician.class)
            .addAsResource("import.sql", "import.sql")
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
      assertEquals(initialSize + 1, cdservice.listAll().size());

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

   @Test
   public void should_check_images()
   {
      // Gets all the images
      int initialSize = cdservice.findAllImages().size();

      // Inserts an object with no image into the database and checks it has the same initial size
      CD cd = new CD();
      cd.setTitle("Dummy value");
      cdservice.persist(cd);
      assertEquals(initialSize, cdservice.findAllImages().size());

      // Inserts an object with no image into the database and checks it has the same initial size
      CD cdImg = new CD();
      cdImg.setTitle("Dummy value");
      cdImg.setImageURL("Dummy value");
      cdservice.persist(cdImg);
      assertEquals(initialSize + 1, cdservice.findAllImages().size());
   }

   @Test
   public void should_check_cds_by_genre()
   {
      assertEquals(0, cdservice.findByGenre(999999L).size());
      assertEquals(5, cdservice.findByGenre(-9L).size());
      assertEquals(4, cdservice.findByGenre(-13L).size());
   }
}
