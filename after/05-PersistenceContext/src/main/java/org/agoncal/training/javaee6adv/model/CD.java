package org.agoncal.training.javaee6adv.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@DiscriminatorValue("C")
public class CD extends Item implements Serializable
{

   @Column(name = "total_duration")
   private Float totalDuration;

   @ManyToOne
   private MajorLabel label;

   @ManyToOne
   private Genre genre;

   @ManyToMany
   private Set<Musician> musicians = new HashSet<Musician>();

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof CD))
      {
         return false;
      }
      CD other = (CD) obj;
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

   public Float getTotalDuration()
   {
      return totalDuration;
   }

   public void setTotalDuration(Float totalDuration)
   {
      this.totalDuration = totalDuration;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      result += ", version: " + version;
      if (title != null && !title.trim().isEmpty())
         result += ", title: " + title;
      if (price != null)
         result += ", price: " + price;
      if (description != null && !description.trim().isEmpty())
         result += ", description: " + description;
      if (imageURL != null && !imageURL.trim().isEmpty())
         result += ", imageURL: " + imageURL;
      if (totalDuration != null)
         result += ", totalDuration: " + totalDuration;
      return result;
   }

   public MajorLabel getLabel()
   {
      return this.label;
   }

   public void setLabel(final MajorLabel label)
   {
      this.label = label;
   }

   public Genre getGenre()
   {
      return this.genre;
   }

   public void setGenre(final Genre genre)
   {
      this.genre = genre;
   }

   public Set<Musician> getMusicians()
   {
      return this.musicians;
   }

   public void setMusicians(final Set<Musician> musicians)
   {
      this.musicians = musicians;
   }
}