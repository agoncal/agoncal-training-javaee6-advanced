package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.model.Category;
import org.agoncal.training.javaee6adv.model.Language;
import org.agoncal.training.javaee6adv.service.AbstractService;
import org.agoncal.training.javaee6adv.service.CategoryService;
import org.agoncal.training.javaee6adv.util.Resources;
import org.agoncal.training.javaee6adv.view.CategoryBean;
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
public class CategoryBeanTest
{

   @Inject
   private CategoryBean categorybean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(CategoryBean.class)
            .addClass(Resources.class)
            .addClass(AbstractService.class)
            .addClass(CategoryService.class)
            .addClass(Category.class)
            .addClass(Author.class)
            .addClass(Language.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(categorybean);
   }

   @Test
   public void should_crud()
   {
      // Creates an object
      Category category = new Category();
      category.setName("Dummy value");

      // Inserts the object into the database
      categorybean.setCategory(category);
      categorybean.create();
      categorybean.update();
      category = categorybean.getCategory();
      assertNotNull(category.getId());

      // Finds the object from the database and checks it's the right one
      category = categorybean.findById(category.getId());
      assertEquals("Dummy value", category.getName());

      // Deletes the object from the database and checks it's not there anymore
      categorybean.setId(category.getId());
      categorybean.create();
      categorybean.delete();
      category = categorybean.findById(category.getId());
      assertNull(category);
   }

   @Test
   public void should_paginate()
   {
      // Creates an empty example
      Category example = new Category();

      // Paginates through the example
      categorybean.setExample(example);
      categorybean.paginate();
      assertTrue((categorybean.getPageItems().size()==categorybean.getPageSize()) || (categorybean.getPageItems().size()==categorybean.getCount()));
   }
}
