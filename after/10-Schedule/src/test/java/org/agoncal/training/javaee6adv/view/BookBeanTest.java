package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.model.Book;
import org.agoncal.training.javaee6adv.model.Category;
import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.Language;
import org.agoncal.training.javaee6adv.model.Publisher;
import org.agoncal.training.javaee6adv.service.AbstractService;
import org.agoncal.training.javaee6adv.service.BookService;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
            .addClass(AbstractService.class)
            .addClass(ResourceProducer.class)
            .addClass(BookService.class)
            .addClass(Book.class)
            .addClass(Item.class)
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
      assertTrue((bookbean.getPageItems().size() == bookbean.getPageSize()) || (bookbean.getPageItems().size() == bookbean.getCount()));
   }

   @Test
   public void should_check_books_by_category()
   {
      bookbean.setCategoryId(999999L);
      bookbean.findByCategory();
      assertEquals(0, bookbean.getBooksPerCateogry().size());

      bookbean.setCategoryId(-5L);
      bookbean.findByCategory();
      assertEquals(5, bookbean.getBooksPerCateogry().size());

      bookbean.setCategoryId(-12L);
      bookbean.findByCategory();
      assertEquals(7, bookbean.getBooksPerCateogry().size());
   }
}
