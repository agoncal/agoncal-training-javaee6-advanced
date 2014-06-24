package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.*;
import org.agoncal.training.javaee6adv.service.BookService;
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
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BookServiceTest
{

   @Inject
   private BookService bookservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(Resources.class)
            .addClass(AbstractService.class)
            .addClass(BookService.class)
            .addClass(Book.class)
            .addClass(Language.class)
            .addClass(Category.class)
            .addClass(Author.class)
            .addClass(Publisher.class)
            .addAsResource("import.sql", "import.sql")
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
      assertEquals(initialSize+1, bookservice.listAll().size());

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

   @Test
   public void should_check_images()
   {
      // Gets all the images
      int initialSize = bookservice.findAllImages().size();

      // Inserts an object with no image into the database and checks it has the same initial size
      Book book = new Book();
      book.setTitle("Dummy value");
      book.setIsbn("Dummy value");
      bookservice.persist(book);
      assertEquals(initialSize, bookservice.findAllImages().size());

      // Inserts an object with no image into the database and checks it has the same initial size
      Book bookImg = new Book();
      bookImg.setTitle("Dummy value");
      bookImg.setIsbn("Dummy value");
      bookImg.setImageURL("Dummy value");
      bookservice.persist(bookImg);
      assertEquals(initialSize+1, bookservice.findAllImages().size());
   }

   @Test
   public void should_check_books_by_category()
   {
      assertEquals(0, bookservice.findByCategory(999999L).size());
      assertEquals(5, bookservice.findByCategory(-5L).size());
      assertEquals(7, bookservice.findByCategory(-12L).size());
   }
}
