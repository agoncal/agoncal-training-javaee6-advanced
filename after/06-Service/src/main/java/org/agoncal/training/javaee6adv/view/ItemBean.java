package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.service.ItemService;

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
 * Backing bean for Item entities.
 * <p/>
 * This class provides CRUD functionality for all Item entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@ConversationScoped
public class ItemBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Item entities
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

   private Item item;

   public Item getItem()
   {
      return this.item;
   }

   public void setItem(Item item)
   {
      this.item = item;
   }

   @Inject
   private Conversation conversation;

   @Inject
   private ItemService service;

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
         this.item = this.example;
      }
      else
      {
         this.item = findById(getId());
      }
   }

   public Item findById(Long id)
   {

      return this.service.findById(id);
   }

   /*
    * Support updating and deleting Item entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.service.persist(this.item);
            return "search?faces-redirect=true";
         }
         else
         {
            this.service.merge(this.item);
            return "view?faces-redirect=true&id=" + this.item.getId();
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
         Item deletableEntity = findById(getId());

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
    * Support searching Item entities with pagination
    */

   private int page;
   private long count;
   private List<Item> pageItems;

   private Item example = new Item();

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

   public Item getExample()
   {
      return this.example;
   }

   public void setExample(Item example)
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

   public List<Item> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Item entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Item> getAll()
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

            return String.valueOf(((Item) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Item add = new Item();

   public Item getAdd()
   {
      return this.add;
   }

   public Item getAdded()
   {
      Item added = this.add;
      this.add = new Item();
      return added;
   }
}