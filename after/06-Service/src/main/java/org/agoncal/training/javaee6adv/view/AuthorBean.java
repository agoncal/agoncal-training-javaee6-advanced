package org.agoncal.training.javaee6adv.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.service.AuthorService;

/**
 * Backing bean for Author entities.
 * <p>
 * This class provides CRUD functionality for all Author entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@ConversationScoped
public class AuthorBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Author entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private Author author;

   public Author getAuthor()
   {
      return this.author;
   }

   public void setAuthor(Author author)
   {
      this.author = author;
   }

   @Inject
   private Conversation conversation;

   @Inject
   private AuthorService service;

   public String create()
   {

      this.conversation.begin();
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
      }

      if (this.id == null)
      {
         this.author = this.example;
      }
      else
      {
         this.author = findById(getId());
      }
   }

   public Author findById(Long id)
   {

      return this.service.findById(id);
   }

   /*
    * Support updating and deleting Author entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.service.persist(this.author);
            return "search?faces-redirect=true";
         }
         else
         {
            this.service.merge(this.author);
            return "view?faces-redirect=true&id=" + this.author.getId();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         Author deletableEntity = findById(getId());

         this.service.remove(deletableEntity);
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching Author entities with pagination
    */

   private int page;
   private long count;
   private List<Author> pageItems;

   private Author example = new Author();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public Author getExample()
   {
      return this.example;
   }

   public void setExample(Author example)
   {
      this.example = example;
   }

   public void search()
   {
      this.page = 0;
   }

   public void paginate()
   {

      // Populate this.count
      this.count = this.service.count(example);

      // Populate this.pageItems
      this.pageItems = this.service.page(example, page, getPageSize());
   }

   public List<Author> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Author entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Author> getAll()
   {
      return this.service.listAll();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return service.findById(Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Author) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Author add = new Author();

   public Author getAdd()
   {
      return this.add;
   }

   public Author getAdded()
   {
      Author added = this.add;
      this.add = new Author();
      return added;
   }
}