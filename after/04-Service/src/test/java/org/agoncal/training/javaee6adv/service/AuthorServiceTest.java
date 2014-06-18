package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.model.Language;
import org.agoncal.training.javaee6adv.service.AuthorService;
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
public class AuthorServiceTest
{

   @Inject
   private AuthorService authorservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(Resources.class)
            .addClass(AbstractService.class)
            .addClass(AuthorService.class)
            .addClass(Author.class)
            .addClass(Language.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(authorservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = authorservice.listAll().size();

      // Creates an object
      Author author = new Author();
      author.setFirstName("Dummy value");
      author.setLastName("Dummy value");

      // Inserts the object into the database
      author = authorservice.persist(author);
      assertNotNull(author.getId());
      assertEquals(initialSize+1, authorservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      author = authorservice.findById(author.getId());
      assertEquals("Dummy value", author.getFirstName());

      // Updates the object
      author.setFirstName("A new value");
      author = authorservice.merge(author);

      // Finds the object from the database and checks it has been updated
      author = authorservice.findById(author.getId());
      assertEquals("A new value", author.getFirstName());

      // Deletes the object from the database and checks it's not there anymore
      authorservice.remove(author);
      assertEquals(initialSize, authorservice.listAll().size());
   }
}
