package org.agoncal.training.javaee6adv.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.agoncal.training.javaee6adv.model.Customer;
import javax.persistence.ManyToOne;
import org.agoncal.training.javaee6adv.model.OrderLine;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import org.agoncal.training.javaee6adv.model.CreditCardType;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class PurchaseOrder implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private int quantity;

   @Column(name = "order_date")
   @Temporal(TemporalType.DATE)
   private Date orderDate;

   @ManyToOne
   private Customer customer;

   @OneToMany
   private Set<OrderLine> orderLines = new HashSet<OrderLine>();

   @Embedded
   @Valid
   private Address deliveryAddress = new Address();

   @Embedded
   @Valid
   private CreditCard creditCard = new CreditCard();

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof PurchaseOrder))
      {
         return false;
      }
      PurchaseOrder other = (PurchaseOrder) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public int getQuantity()
   {
      return quantity;
   }

   public void setQuantity(int quantity)
   {
      this.quantity = quantity;
   }

   public Date getOrderDate()
   {
      return orderDate;
   }

   public void setOrderDate(Date orderDate)
   {
      this.orderDate = orderDate;
   }

   public Customer getCustomer()
   {
      return this.customer;
   }

   public void setCustomer(final Customer customer)
   {
      this.customer = customer;
   }

   public Set<OrderLine> getOrderLines()
   {
      return this.orderLines;
   }

   public void setOrderLines(final Set<OrderLine> orderLines)
   {
      this.orderLines = orderLines;
   }

   public String getStreet1()
   {
      return deliveryAddress.getStreet1();
   }

   public void setStreet1(String street1)
   {
      this.deliveryAddress.setStreet1(street1);
   }

   public String getStreet2()
   {
      return deliveryAddress.getStreet2();
   }

   public void setStreet2(String street2)
   {
      this.deliveryAddress.setStreet2(street2);
   }

   public String getCity()
   {
      return deliveryAddress.getCity();
   }

   public void setCity(String city)
   {
      this.deliveryAddress.setCity(city);
   }

   public String getState()
   {
      return deliveryAddress.getState();
   }

   public void setState(String state)
   {
      this.deliveryAddress.setState(state);
   }

   public String getZipcode()
   {
      return deliveryAddress.getZipcode();
   }

   public void setZipcode(String zipcode)
   {
      this.deliveryAddress.setZipcode(zipcode);
   }

   public String getCountry()
   {
      return deliveryAddress.getCountry();
   }

   public void setCountry(String country)
   {
      this.deliveryAddress.setCountry(country);
   }

   public String getCreditCardNumber()
   {
      return creditCard.getCreditCardNumber();
   }

   public void setCreditCardNumber(String creditCardNumber)
   {
      this.creditCard.setCreditCardNumber(creditCardNumber);
   }

   public CreditCardType getCreditCardType()
   {
      return creditCard.getCreditCardType();
   }

   public void setCreditCardType(CreditCardType creditCardType)
   {
      this.creditCard.setCreditCardType(creditCardType);
   }

   public String getCreditCardExpDate()
   {
      return creditCard.getCreditCardExpDate();
   }

   public void setCreditCardExpDate(String creditCardExpDate)
   {
      this.creditCard.setCreditCardExpDate(creditCardExpDate);
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      result += ", version: " + version;
      result += ", quantity: " + quantity;
      if (orderDate != null)
         result += ", orderDate: " + orderDate;
      if (customer != null)
         result += ", customer: " + customer;
      if (orderLines != null)
         result += ", orderLines: " + orderLines;
      if (deliveryAddress.getStreet1() != null && !deliveryAddress.getStreet1().trim().isEmpty())
         result += ", street1: " + deliveryAddress.getStreet1();
      if (deliveryAddress.getStreet2() != null && !deliveryAddress.getStreet2().trim().isEmpty())
         result += ", street2: " + deliveryAddress.getStreet2();
      if (deliveryAddress.getCity() != null && !deliveryAddress.getCity().trim().isEmpty())
         result += ", city: " + deliveryAddress.getCity();
      if (deliveryAddress.getState() != null && !deliveryAddress.getState().trim().isEmpty())
         result += ", state: " + deliveryAddress.getState();
      if (deliveryAddress.getZipcode() != null && !deliveryAddress.getZipcode().trim().isEmpty())
         result += ", zipcode: " + deliveryAddress.getZipcode();
      if (deliveryAddress.getCountry() != null && !deliveryAddress.getCountry().trim().isEmpty())
         result += ", country: " + deliveryAddress.getCountry();
      if (creditCard.getCreditCardNumber() != null && !creditCard.getCreditCardNumber().trim().isEmpty())
         result += ", creditCardNumber: " + creditCard.getCreditCardNumber();
      if (creditCard.getCreditCardType() != null)
         result += ", creditCardType: " + creditCard.getCreditCardType();
      if (creditCard.getCreditCardExpDate() != null && !creditCard.getCreditCardExpDate().trim().isEmpty())
         result += ", creditCardExpDate: " + creditCard.getCreditCardExpDate();
      return result;
   }
}