package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.*;
import org.agoncal.training.javaee6adv.view.BookBean;
import javax.inject.Inject;
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
public class BookBeanTest
{

   @Inject
   private BookBean bookbean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(BookBean.class)
            .addClass(Book.class)
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
      Assert.assertNotNull(bookbean);
   }

   @Test
   public void should_crud()
   {
      // Creates an object
      Book book = new Book();
      book.setTitle("Dummy value");
      book.setIsbn("Dummy value");

      // Inserts the object into the database
      bookbean.setBook(book);
      bookbean.create();
      bookbean.update();
      book = bookbean.getBook();
      assertNotNull(book.getId());

      // Finds the object from the database and checks it's the right one
      book = bookbean.findById(book.getId());
      assertEquals("Dummy value", book.getTitle());

      // Deletes the object from the database and checks it's not there anymore
      bookbean.setId(book.getId());
      bookbean.create();
      bookbean.delete();
      book = bookbean.findById(book.getId());
      assertNull(book);
   }

   @Test
   public void should_paginate()
   {
      // Creates an empty example
      Book example = new Book();

      // Paginates through the example
      bookbean.setExample(example);
      bookbean.paginate();
      assertEquals(bookbean.getCount(), bookbean.getPageItems().size());
   }
}
