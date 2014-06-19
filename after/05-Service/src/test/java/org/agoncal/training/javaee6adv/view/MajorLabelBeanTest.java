package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.MajorLabel;
import org.agoncal.training.javaee6adv.service.AbstractService;
import org.agoncal.training.javaee6adv.service.MajorLabelService;
import org.agoncal.training.javaee6adv.util.Resources;
import org.agoncal.training.javaee6adv.view.MajorLabelBean;
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
public class MajorLabelBeanTest
{

   @Inject
   private MajorLabelBean majorlabelbean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(MajorLabelBean.class)
            .addClass(Resources.class)
            .addClass(AbstractService.class)
            .addClass(MajorLabelService.class)
            .addClass(MajorLabel.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(majorlabelbean);
   }

   @Test
   public void should_crud()
   {
      // Creates an object
      MajorLabel majorLabel = new MajorLabel();
      majorLabel.setName("Dummy value");

      // Inserts the object into the database
      majorlabelbean.setMajorLabel(majorLabel);
      majorlabelbean.create();
      majorlabelbean.update();
      majorLabel = majorlabelbean.getMajorLabel();
      assertNotNull(majorLabel.getId());

      // Finds the object from the database and checks it's the right one
      majorLabel = majorlabelbean.findById(majorLabel.getId());
      assertEquals("Dummy value", majorLabel.getName());

      // Deletes the object from the database and checks it's not there anymore
      majorlabelbean.setId(majorLabel.getId());
      majorlabelbean.create();
      majorlabelbean.delete();
      majorLabel = majorlabelbean.findById(majorLabel.getId());
      assertNull(majorLabel);
   }

   @Test
   public void should_paginate()
   {
      // Creates an empty example
      MajorLabel example = new MajorLabel();

      // Paginates through the example
      majorlabelbean.setExample(example);
      majorlabelbean.paginate();
      assertEquals(majorlabelbean.getCount(), majorlabelbean.getPageItems().size());
   }
}
