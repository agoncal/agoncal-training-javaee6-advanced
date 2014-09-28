package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.Category;
import org.agoncal.training.javaee6adv.service.CategoryService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Backing bean for Category entities.
 * <p/>
 * This class provides CRUD functionality for all Category entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@ConversationScoped
public class CategoryBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Category entities
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

   private Category category;

   public Category getCategory()
   {
      return this.category;
   }

   public void setCategory(Category category)
   {
      this.category = category;
   }

   @Inject
   private Conversation conversation;

   @Inject
   private CategoryService service;

   public String create()
   {

      this.conversation.begin();
      this.conversation.setTimeout(1800000L);
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
         this.conversation.setTimeout(1800000L);
      }

      if (this.id == null)
      {
         this.category = this.example;
      }
      else
      {
         this.category = findById(getId());
      }
   }

   public Category findById(Long id)
   {

      return this.service.findById(id);
   }

   /*
    * Support updating and deleting Category entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.service.persist(this.category);
            return "search?faces-redirect=true";
         }
         else
         {
            this.service.merge(this.category);
            return "view?faces-redirect=true&id=" + this.category.getId();
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
         Category deletableEntity = findById(getId());

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
    * Support searching Category entities with pagination
    */

   private int page;
   private long count;
   private List<Category> pageItems;

   private Category example = new Category();

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

   public Category getExample()
   {
      return this.example;
   }

   public void setExample(Category example)
   {
      this.example = example;
   }

   public String search()
   {
      this.page = 0;
      return null;
   }

   public void paginate()
   {

      // Populate this.count
      this.count = service.count(example);

      // Populate this.pageItems
      this.pageItems = service.page(example, page, getPageSize());
   }

   public List<Category> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Category entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Category> getAll()
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

            return String.valueOf(((Category) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Category add = new Category();

   public Category getAdd()
   {
      return this.add;
   }

   public Category getAdded()
   {
      Category added = this.add;
      this.add = new Category();
      return added;
   }
}