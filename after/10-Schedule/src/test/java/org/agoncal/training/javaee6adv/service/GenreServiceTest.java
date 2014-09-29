package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Genre;
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
public class GenreServiceTest
{

   @Inject
   private GenreService genreservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AbstractService.class)
            .addClass(ResourceProducer.class)
            .addClass(GenreService.class)
            .addClass(Genre.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(genreservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = genreservice.listAll().size();

      // Creates an object
      Genre genre = new Genre();
      genre.setName("Dummy value");

      // Inserts the object into the database
      genre = genreservice.persist(genre);
      assertNotNull(genre.getId());
      assertEquals(initialSize + 1, genreservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      genre = genreservice.findById(genre.getId());
      assertEquals("Dummy value", genre.getName());

      // Updates the object
      genre.setName("A new value");
      genre = genreservice.merge(genre);

      // Finds the object from the database and checks it has been updated
      genre = genreservice.findById(genre.getId());
      assertEquals("A new value", genre.getName());

      // Deletes the object from the database and checks it's not there anymore
      genreservice.remove(genre);
      assertEquals(initialSize, genreservice.listAll().size());
   }
}
