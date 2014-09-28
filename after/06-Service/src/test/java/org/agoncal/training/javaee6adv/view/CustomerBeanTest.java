package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.Address;
import org.agoncal.training.javaee6adv.model.Customer;
import org.agoncal.training.javaee6adv.service.AbstractService;
import org.agoncal.training.javaee6adv.service.CustomerService;
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
public class CustomerBeanTest
{

   @Inject
   private CustomerBean customerbean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(CustomerBean.class)
            .addClass(AbstractService.class)
            .addClass(CustomerService.class)
            .addClass(Customer.class)
            .addClass(Address.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(customerbean);
   }


   @Test
   public void should_crud()
   {
      // Creates an object
      Customer customer = new Customer();
      customer.setFirstname("Dummy value");
      customer.setLastname("Dummy value");
      customer.setStreet1("Dummy value");
      customer.setCity("Dummy value");
      customer.setZipcode("Dummy");
      customer.setCountry("Dummy value");

      // Inserts the object into the database
      customerbean.setCustomer(customer);
      customerbean.create();
      customerbean.update();
      customer = customerbean.getCustomer();
      assertNotNull(customer.getId());

      // Finds the object from the database and checks it's the right one
      customer = customerbean.findById(customer.getId());
      assertEquals("Dummy value", customer.getFirstname());

      // Deletes the object from the database and checks it's not there anymore
      customerbean.setId(customer.getId());
      customerbean.create();
      customerbean.delete();
      customer = customerbean.findById(customer.getId());
      assertNull(customer);
   }

   @Test
   public void should_paginate()
   {
      // Creates an empty example
      Customer example = new Customer();

      // Paginates through the example
      customerbean.setExample(example);
      customerbean.paginate();
      assertTrue((customerbean.getPageItems().size() == customerbean.getPageSize()) || (customerbean.getPageItems().size() == customerbean.getCount()));
   }
}
