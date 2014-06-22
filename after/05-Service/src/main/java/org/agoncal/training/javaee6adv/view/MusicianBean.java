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

import org.agoncal.training.javaee6adv.model.Musician;
import org.agoncal.training.javaee6adv.service.MusicianService;

/**
 * Backing bean for Musician entities.
 * <p>
 * This class provides CRUD functionality for all Musician entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@ConversationScoped
public class MusicianBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Musician entities
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

   private Musician musician;

   public Musician getMusician()
   {
      return this.musician;
   }

   public void setMusician(Musician musician)
   {
      this.musician = musician;
   }

   @Inject
   private Conversation conversation;

   @Inject
   private MusicianService service;

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
         this.musician = this.example;
      }
      else
      {
         this.musician = findById(getId());
      }
   }

   public Musician findById(Long id)
   {

      return this.service.findById(id);
   }

   /*
    * Support updating and deleting Musician entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.service.persist(this.musician);
            return "search?faces-redirect=true";
         }
         else
         {
            this.service.merge(this.musician);
            return "view?faces-redirect=true&id=" + this.musician.getId();
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
         Musician deletableEntity = findById(getId());

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
    * Support searching Musician entities with pagination
    */

   private int page;
   private long count;
   private List<Musician> pageItems;

   private Musician example = new Musician();

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

   public Musician getExample()
   {
      return this.example;
   }

   public void setExample(Musician example)
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
      this.count = service.count(example);

      // Populate this.pageItems
      this.pageItems = service.page(example, page, getPageSize());
   }

   public List<Musician> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Musician entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Musician> getAll()
   {
      return service.listAll();
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

            return String.valueOf(((Musician) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Musician add = new Musician();

   public Musician getAdd()
   {
      return this.add;
   }

   public Musician getAdded()
   {
      Musician added = this.add;
      this.add = new Musician();
      return added;
   }
}