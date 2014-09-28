package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Musician;
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
public class MusicianServiceTest
{

   @Inject
   private MusicianService musicianservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AbstractService.class)
            .addClass(MusicianService.class)
            .addClass(Musician.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(musicianservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = musicianservice.listAll().size();

      // Creates an object
      Musician musician = new Musician();
      musician.setFirstName("Dummy value");
      musician.setLastName("Dummy value");

      // Inserts the object into the database
      musician = musicianservice.persist(musician);
      assertNotNull(musician.getId());
      assertEquals(initialSize + 1, musicianservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      musician = musicianservice.findById(musician.getId());
      assertEquals("Dummy value", musician.getFirstName());

      // Updates the object
      musician.setFirstName("A new value");
      musician = musicianservice.merge(musician);

      // Finds the object from the database and checks it has been updated
      musician = musicianservice.findById(musician.getId());
      assertEquals("A new value", musician.getFirstName());

      // Deletes the object from the database and checks it's not there anymore
      musicianservice.remove(musician);
      assertEquals(initialSize, musicianservice.listAll().size());
   }
}
