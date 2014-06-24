package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.model.Language;
import org.agoncal.training.javaee6adv.service.AbstractService;
import org.agoncal.training.javaee6adv.service.AuthorService;
import org.agoncal.training.javaee6adv.util.Resources;
import org.agoncal.training.javaee6adv.view.AuthorBean;
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
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AuthorBeanTest
{

   @Inject
   private AuthorBean authorbean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AuthorBean.class)
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
      Assert.assertNotNull(authorbean);
   }

   @Test
   public void should_crud()
   {
      // Creates an object
      Author author = new Author();
      author.setFirstName("Dummy value");
      author.setLastName("Dummy value");

      // Inserts the object into the database
      authorbean.setAuthor(author);
      authorbean.create();
      authorbean.update();
      author = authorbean.getAuthor();
      assertNotNull(author.getId());

      // Finds the object from the database and checks it's the right one
      author = authorbean.findById(author.getId());
      assertEquals("Dummy value", author.getFirstName());

      // Deletes the object from the database and checks it's not there anymore
      authorbean.setId(author.getId());
      authorbean.create();
      authorbean.delete();
      author = authorbean.findById(author.getId());
      assertNull(author);
   }

   @Test
   public void should_paginate()
   {
      // Creates an empty example
      Author example = new Author();

      // Paginates through the example
      authorbean.setExample(example);
      authorbean.paginate();
      assertTrue((authorbean.getPageItems().size()==authorbean.getPageSize()) || (authorbean.getPageItems().size()==authorbean.getCount()));
   }
}
