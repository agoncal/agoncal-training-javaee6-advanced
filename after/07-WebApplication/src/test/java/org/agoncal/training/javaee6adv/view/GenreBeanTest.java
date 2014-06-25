package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.Genre;
import org.agoncal.training.javaee6adv.service.AbstractService;
import org.agoncal.training.javaee6adv.service.GenreService;
import org.agoncal.training.javaee6adv.util.Resources;
import org.agoncal.training.javaee6adv.view.GenreBean;
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
public class GenreBeanTest
{

   @Inject
   private GenreBean genrebean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(GenreBean.class)
            .addClass(Resources.class)
            .addClass(AbstractService.class)
            .addClass(GenreService.class)
            .addClass(Genre.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(genrebean);
   }

   @Test
   public void should_crud()
   {
      // Creates an object
      Genre genre = new Genre();
      genre.setName("Dummy value");

      // Inserts the object into the database
      genrebean.setGenre(genre);
      genrebean.create();
      genrebean.update();
      genre = genrebean.getGenre();
      assertNotNull(genre.getId());

      // Finds the object from the database and checks it's the right one
      genre = genrebean.findById(genre.getId());
      assertEquals("Dummy value", genre.getName());

      // Deletes the object from the database and checks it's not there anymore
      genrebean.setId(genre.getId());
      genrebean.create();
      genrebean.delete();
      genre = genrebean.findById(genre.getId());
      assertNull(genre);
   }

   @Test
   public void should_paginate()
   {
      // Creates an empty example
      Genre example = new Genre();

      // Paginates through the example
      genrebean.setExample(example);
      genrebean.paginate();
      assertTrue((genrebean.getPageItems().size()==genrebean.getPageSize()) || (genrebean.getPageItems().size()==genrebean.getCount()));
   }
}
