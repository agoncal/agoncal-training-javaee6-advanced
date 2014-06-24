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

import org.agoncal.training.javaee6adv.model.CD;
import org.agoncal.training.javaee6adv.service.CDService;

/**
 * Backing bean for CD entities.
 * <p>
 * This class provides CRUD functionality for all CD entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@ConversationScoped
public class CDBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving CD entities
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

   private CD CD;

   public CD getCD()
   {
      return this.CD;
   }

   public void setCD(CD CD)
   {
      this.CD = CD;
   }

   @Inject
   private Conversation conversation;

   @Inject
   private CDService service;

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
         this.CD = this.example;
      }
      else
      {
         this.CD = findById(getId());
      }
   }

   public CD findById(Long id)
   {

      return this.service.findByIdWithRelations(id);
   }

   /*
    * Support updating and deleting CD entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.service.persist(this.CD);
            return "search?faces-redirect=true";
         }
         else
         {
            this.service.merge(this.CD);
            return "view?faces-redirect=true&id=" + this.CD.getId();
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
         CD deletableEntity = findById(getId());

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
    * Support searching CD entities with pagination
    */

   private int page;
   private long count;
   private List<CD> pageItems;

   private CD example = new CD();

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

   public CD getExample()
   {
      return this.example;
   }

   public void setExample(CD example)
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

   public List<CD> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back CD entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<CD> getAll()
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

            return String.valueOf(((CD) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private CD add = new CD();

   public CD getAdd()
   {
      return this.add;
   }

   public CD getAdded()
   {
      CD added = this.add;
      this.add = new CD();
      return added;
   }
}