package org.agoncal.training.javaee6adv.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
         result += "creditCardNumber: " + creditCardNumber;
      if (creditCardExpDate != null && !creditCardExpDate.trim().isEmpty())
         result += ", creditCardExpDate: " + creditCardExpDate;
      return result;
   }
}