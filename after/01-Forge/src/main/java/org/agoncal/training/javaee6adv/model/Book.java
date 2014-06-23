package org.agoncal.training.javaee6adv.model;

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
import javax.persistence.Enumerated;
import org.agoncal.training.javaee6adv.model.Language;
import org.agoncal.training.javaee6adv.model.Category;
import javax.persistence.ManyToOne;
import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.model.Publisher;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Book implements Serializable
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
   @Size(min = 1, max = 3000)
   private String description;

   @Column(name = "image_url")
   private String imageURL;

   @Column(length = 15)
   @NotNull
   private String isbn;

   @Column(name = "nb_of_pages")
   @Min(1)
   private Integer nbOfPages;

   @Column(name = "publication_date")
   @Temporal(TemporalType.DATE)
   @Past
   private Date publicationDate;

   @Enumerated
   private Language language;

   @ManyToOne
   private Category category;

   @ManyToOne
   private Author author;

   @ManyToOne
   private Publisher publisher;

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
      if (!(obj instanceof Book))
      {
         return false;
      }
      Book other = (Book) obj;
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

   public String getIsbn()
   {
      return isbn;
   }

   public void setIsbn(String isbn)
   {
      this.isbn = isbn;
   }

   public Integer getNbOfPages()
   {
      return nbOfPages;
   }

   public void setNbOfPages(Integer nbOfPages)
   {
      this.nbOfPages = nbOfPages;
   }

   public Date getPublicationDate()
   {
      return publicationDate;
   }

   public void setPublicationDate(Date publicationDate)
   {
      this.publicationDate = publicationDate;
   }

   public Language getLanguage()
   {
      return language;
   }

   public void setLanguage(Language language)
   {
      this.language = language;
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
      if (isbn != null && !isbn.trim().isEmpty())
         result += ", isbn: " + isbn;
      if (nbOfPages != null)
         result += ", nbOfPages: " + nbOfPages;
      if (publicationDate != null)
         result += ", publicationDate: " + publicationDate;
      if (language != null)
         result += ", language: " + language;
      return result;
   }

   public Category getCategory()
   {
      return this.category;
   }

   public void setCategory(final Category category)
   {
      this.category = category;
   }

   public Author getAuthor()
   {
      return this.author;
   }

   public void setAuthor(final Author author)
   {
      this.author = author;
   }

   public Publisher getPublisher()
   {
      return this.publisher;
   }

   public void setPublisher(final Publisher publisher)
   {
      this.publisher = publisher;
   }
}