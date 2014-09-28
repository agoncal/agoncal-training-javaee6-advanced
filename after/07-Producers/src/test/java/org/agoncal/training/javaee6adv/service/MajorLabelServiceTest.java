package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.MajorLabel;
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

@RunWith(Arquillian.class)
public class MajorLabelServiceTest
{

   @Inject
   private MajorLabelService majorlabelservice;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(AbstractService.class)
            .addClass(ResourceProducer.class)
            .addClass(MajorLabelService.class)
            .addClass(MajorLabel.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(majorlabelservice);
   }

   @Test
   public void should_crud()
   {
      // Gets all the objects
      int initialSize = majorlabelservice.listAll().size();

      // Creates an object
      MajorLabel majorLabel = new MajorLabel();
      majorLabel.setName("Dummy value");

      // Inserts the object into the database
      majorLabel = majorlabelservice.persist(majorLabel);
      assertNotNull(majorLabel.getId());
      assertEquals(initialSize + 1, majorlabelservice.listAll().size());

      // Finds the object from the database and checks it's the right one
      majorLabel = majorlabelservice.findById(majorLabel.getId());
      assertEquals("Dummy value", majorLabel.getName());

      // Updates the object
      majorLabel.setName("A new value");
      majorLabel = majorlabelservice.merge(majorLabel);

      // Finds the object from the database and checks it has been updated
      majorLabel = majorlabelservice.findById(majorLabel.getId());
      assertEquals("A new value", majorLabel.getName());

      // Deletes the object from the database and checks it's not there anymore
      majorlabelservice.remove(majorLabel);
      assertEquals(initialSize, majorlabelservice.listAll().size());
   }
}
