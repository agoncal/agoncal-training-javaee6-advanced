package org.agoncal.training.javaee6adv.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
@DiscriminatorValue("B")
public class Book extends Item implements Serializable
{

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
      if (title != null && !title.trim().isEmpty())
         result += "title: " + title;
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