package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.model.Book;
import org.agoncal.training.javaee6adv.model.Category;
import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.Language;
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
public class BookServiceTest
{

   @Inject
   private BookService bookservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AbstractService.class)
            .addClass(BookService.class)
            .addClass(Book.class)
            .addClass(Item.class)
            .addClass(Language.class)
            .addClass(Category.class)
            .addClass(Author.class)
            .addClass(Publisher.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(bookservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = bookservice.listAll().size();

      // Creates an object
      Book book = new Book();
      book.setTitle("Dummy value");
      book.setIsbn("Dummy value");

      // Inserts the object into the database
      book = bookservice.persist(book);
      assertNotNull(book.getId());
      assertEquals(initialSize + 1, bookservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      book = bookservice.findById(book.getId());
      assertEquals("Dummy value", book.getTitle());

      // Updates the object
      book.setTitle("A new value");
      book = bookservice.merge(book);

      // Finds the object from the database and checks it has been updated
      book = bookservice.findById(book.getId());
      assertEquals("A new value", book.getTitle());

      // Deletes the object from the database and checks it's not there anymore
      bookservice.remove(book);
      assertEquals(initialSize, bookservice.listAll().size());
   }
}
