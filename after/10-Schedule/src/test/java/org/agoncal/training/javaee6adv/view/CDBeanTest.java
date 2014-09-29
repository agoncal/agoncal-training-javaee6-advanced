package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.CD;
import org.agoncal.training.javaee6adv.model.Genre;
import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.MajorLabel;
import org.agoncal.training.javaee6adv.model.Musician;
import org.agoncal.training.javaee6adv.service.AbstractService;
import org.agoncal.training.javaee6adv.service.CDService;
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
public class CDBeanTest
{

   @Inject
   private CDBean cdbean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(CDBean.class)
            .addClass(AbstractService.class)
            .addClass(ResourceProducer.class)
            .addClass(CDService.class)
            .addClass(CD.class)
            .addClass(Item.class)
            .addClass(MajorLabel.class)
            .addClass(Genre.class)
            .addClass(Musician.class)
            .addAsResource("import.sql", "import.sql")
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(cdbean);
   }

   @Test
   public void should_crud()
   {
      // Creates an object
      CD cd = new CD();
      cd.setTitle("Dummy value");

      // Inserts the object into the database
      cdbean.setCD(cd);
      cdbean.create();
      cdbean.update();
      cd = cdbean.getCD();
      assertNotNull(cd.getId());

      // Finds the object from the database and checks it's the right one
      cd = cdbean.findById(cd.getId());
      assertEquals("Dummy value", cd.getTitle());

      // Deletes the object from the database and checks it's not there anymore
      cdbean.setId(cd.getId());
      cdbean.create();
      cdbean.delete();
      cd = cdbean.findById(cd.getId());
      assertNull(cd);
   }

   @Test
   public void should_paginate()
   {
      // Creates an empty example
      CD example = new CD();

      // Paginates through the example
      cdbean.setExample(example);
      cdbean.paginate();
      assertTrue((cdbean.getPageItems().size() == cdbean.getPageSize()) || (cdbean.getPageItems().size() == cdbean.getCount()));
   }

   @Test
   public void should_check_cds_by_genre()
   {
      cdbean.setGenreId(999999L);
      cdbean.findByGenre();
      assertEquals(0, cdbean.getCdsPerGenre().size());

      cdbean.setGenreId(-9L);
      cdbean.findByGenre();
      assertEquals(5, cdbean.getCdsPerGenre().size());

      cdbean.setGenreId(-13L);
      cdbean.findByGenre();
      assertEquals(4, cdbean.getCdsPerGenre().size());
   }
}
