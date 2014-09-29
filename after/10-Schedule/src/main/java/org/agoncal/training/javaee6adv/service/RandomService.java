package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Address;
import org.agoncal.training.javaee6adv.model.Book;
import org.agoncal.training.javaee6adv.model.CD;
import org.agoncal.training.javaee6adv.model.CreditCard;
import org.agoncal.training.javaee6adv.model.CreditCardType;
import org.agoncal.training.javaee6adv.model.Customer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@Stateless
@LocalBean
public class RandomService implements Serializable
{

   private static final Random random = new Random(System.nanoTime());
   public static String[] COUNTRIES = {"France", "USA", "Portugal", "Italy", "United Kingdom"};
   public static String[] STREET = {"Sunset Boulevard", "Ritherdon Rd", "Champs Elysée", "Inacio Alfama", "Fbg St-Antoine"};
   public static String[] ZIPCODE = {"75011", "SW17", "QS 458", "33000", "8QE NE3", "AS 987", "SEP 234567"};
   public static String[] CITY = {"Paris", "New York", "Lisbon", "Milan", "Tokyo", "Montreal", "Sao Paulo", "San Sebastian"};

   @Inject
   private CustomerService customerService;
   @Inject
   private BookService bookService;
   @Inject
   private CDService cdService;

   public Book getBook()
   {
      return selectAtRandom(bookService.listAll());
   }

   public CD getCD()
   {
      return selectAtRandom(cdService.listAll());
   }

   public Customer getCustomer()
   {
      return selectAtRandom(customerService.listAll());
   }

   public Address getAddress()
   {
      Address address = new Address();
      address.setCountry(selectAtRandom(COUNTRIES));
      address.setStreet1(selectAtRandom(STREET));
      address.setZipcode(selectAtRandom(ZIPCODE));
      address.setCity(selectAtRandom(CITY));
      return address;
   }

   public CreditCard getCreditCard()
   {
      CreditCard creditCard = new CreditCard();
      creditCard.setCreditCardNumber(Integer.toString(random.nextInt()));
      creditCard.setCreditCardExpDate("10/20");
      creditCard.setCreditCardType(CreditCardType.MASTER_CARD);
      return creditCard;
   }

   private <T> T selectAtRandom(List<T> list)
   {
      int i = random.nextInt(list.size());
      return list.get(i);
   }

   private <T> T selectAtRandom(T[] array)
   {
      int i = random.nextInt(array.length);
      return array[i];
   }
}