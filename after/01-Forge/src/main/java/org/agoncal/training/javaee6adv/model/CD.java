package org.agoncal.training.javaee6adv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
public class CD implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(length = 50)
   @NotNull
   @Size(min = 1, max = 50)
   private String title;

   @Column
   @Min(1)
   private Float price;

   @Column(length = 3000)
   @Size(max = 3000)
   private String description;

   @Column(name = "image_url")
   private String imageURL;

   @Column(name = "total_duration")
   private Float totalDuration;

   @ManyToOne
   private MajorLabel label;

   @ManyToOne
   private Genre genre;

   @ManyToMany
   private Set<Musician> musicians = new HashSet<Musician>();

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