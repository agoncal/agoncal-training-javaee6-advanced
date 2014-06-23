package org.agoncal.training.javaee6adv.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Entity
public class Address implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   @Size(min = 5, max = 50)
   @NotNull
   private String street1;

   @Column
   private String street2;

   @Column
   @Size(min = 2, max = 50)
   @NotNull
   private String city;

   @Column
   private String state;

   @Column(name = "zip_code")
   @Size(min = 1, max = 10)
   @NotNull
   private String zipcode;

   @Column
   @Size(min = 2, max = 50)
   @NotNull
   private String country;

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
      if (!(obj instanceof Address))
      {
         return false;
      }
      Address other = (Address) obj;
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

   public String getStreet1()
   {
      return street1;
   }

   public void setStreet1(String street1)
   {
      this.street1 = street1;
   }

   public String getStreet2()
   {
      return street2;
   }

   public void setStreet2(String street2)
   {
      this.street2 = street2;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public String getZipcode()
   {
      return zipcode;
   }

   public void setZipcode(String zipcode)
   {
      this.zipcode = zipcode;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      result += ", version: " + version;
      if (street1 != null && !street1.trim().isEmpty())
         result += ", street1: " + street1;
      if (street2 != null && !street2.trim().isEmpty())
         result += ", street2: " + street2;
      if (city != null && !city.trim().isEmpty())
         result += ", city: " + city;
      if (state != null && !state.trim().isEmpty())
         result += ", state: " + state;
      if (zipcode != null && !zipcode.trim().isEmpty())
         result += ", zipcode: " + zipcode;
      if (country != null && !country.trim().isEmpty())
         result += ", country: " + country;
      return result;
   }
}