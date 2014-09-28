package org.agoncal.training.javaee6adv.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("I")
public class Item implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   protected Long id;
   @Version
   @Column(name = "version")
   protected int version;

   @Column(length = 50)
   @NotNull
   @Size(min = 1, max = 50)
   protected String title;

   @Column
   @Min(1)
   protected Float price;

   @Column(length = 3000)
   @Size(min = 1, max = 3000)
   protected String description;

   @Column(name = "image_url")
   protected String imageURL;

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
      if (!(obj instanceof Item))
      {
         return false;
      }
      Item other = (Item) obj;
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

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public Float getPrice()
   {
      return price;
   }

   public void setPrice(Float price)
   {
      this.price = price;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public String getImageURL()
   {
      return imageURL;
   }

   public void setImageURL(String imageURL)
   {
      this.imageURL = imageURL;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (title != null && !title.trim().isEmpty())
         result += "title: " + title;
      if (price != null)
         result += ", price: " + price;
      if (description != null && !description.trim().isEmpty())
         result += ", description: " + description;
      if (imageURL != null && !imageURL.trim().isEmpty())
         result += ", imageURL: " + imageURL;
      return result;
   }
}