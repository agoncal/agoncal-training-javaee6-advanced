package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Address;
import org.agoncal.training.javaee6adv.model.CreditCard;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RandomServiceTest
{

   @Test
   public void should_check_random_address()
   {
      Address address = new RandomService().getAddress();
      assertNotNull(address);
      assertNotNull(address.getCity());
      assertNotNull(address.getCountry());
      assertNotNull(address.getStreet1());
      assertNotNull(address.getZipcode());
   }

   @Test
   public void should_check_random_credit_card()
   {
      CreditCard creditCard = new RandomService().getCreditCard();
      assertNotNull(creditCard);
      assertNotNull(creditCard.getCreditCardExpDate());
      assertNotNull(creditCard.getCreditCardNumber());
      assertNotNull(creditCard.getCreditCardType());
   }
}
