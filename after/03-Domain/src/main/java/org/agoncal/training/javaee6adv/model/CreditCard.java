package org.agoncal.training.javaee6adv.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.persistence.Enumerated;
import org.agoncal.training.javaee6adv.model.CreditCardType;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Embeddable
public class CreditCard implements Serializable
{

   @Column(name = "credit_card_number")
   @Size(min = 1, max = 30)
   @NotNull
   private String creditCardNumber;

   @Enumerated
   @Column(name = "credit_card_type")
   @NotNull
   private CreditCardType creditCardType;

   @Column(name = "credit_card_expiry_date")
   @Size(min = 1, max = 5)
   @NotNull
   private String creditCardExpDate;

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof CreditCard))
      {
         return false;
      }
      CreditCard other = (CreditCard) obj;
      return true;
   }

   public String getCreditCardNumber()
   {
      return creditCardNumber;
   }

   public void setCreditCardNumber(String creditCardNumber)
   {
      this.creditCardNumber = creditCardNumber;
   }

   public CreditCardType getCreditCardType()
   {
      return creditCardType;
   }

   public void setCreditCardType(CreditCardType creditCardType)
   {
      this.creditCardType = creditCardType;
   }

   public String getCreditCardExpDate()
   {
      return creditCardExpDate;
   }

   public void setCreditCardExpDate(String creditCardExpDate)
   {
      this.creditCardExpDate = creditCardExpDate;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (creditCardNumber != null && !creditCardNumber.trim().isEmpty())
         result += ", creditCardNumber: " + creditCardNumber;
      if (creditCardType != null)
         result += ", creditCardType: " + creditCardType;
      if (creditCardExpDate != null && !creditCardExpDate.trim().isEmpty())
         result += ", creditCardExpDate: " + creditCardExpDate;
      return result;
   }
}