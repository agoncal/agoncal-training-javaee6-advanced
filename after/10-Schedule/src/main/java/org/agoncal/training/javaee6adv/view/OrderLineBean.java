package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.model.OrderLine;
import org.agoncal.training.javaee6adv.service.OrderLineService;

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
 * Backing bean for OrderLine entities.
 * <p/>
 * This class provides CRUD functionality for all OrderLine entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@ConversationScoped
public class OrderLineBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving OrderLine entities
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

   private OrderLine orderLine;

   public OrderLine getOrderLine()
   {
      return this.orderLine;
   }

   public void setOrderLine(OrderLine orderLine)
   {
      this.orderLine = orderLine;
   }

   @Inject
   private Conversation conversation;

   @Inject
   private OrderLineService service;

   @Inject
   private FacesContext facesContext;

   public String create()
   {

      this.conversation.begin();
      this.conversation.setTimeout(1800000L);
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (facesContext.isPostback())
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
         this.orderLine = this.example;
      }
      else
      {
         this.orderLine = findById(getId());
      }
   }

   public OrderLine findById(Long id)
   {

      return this.service.findById(id);
   }

   /*
    * Support updating and deleting OrderLine entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.service.persist(this.orderLine);
            return "search?faces-redirect=true";
         }
         else
         {
            this.service.merge(this.orderLine);
            return "view?faces-redirect=true&id=" + this.orderLine.getId();
         }
      }
      catch (Exception e)
      {
         facesContext.addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         OrderLine deletableEntity = findById(getId());

         this.service.remove(deletableEntity);
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         facesContext.addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching OrderLine entities with pagination
    */

   private int page;
   private long count;
   private List<OrderLine> pageItems;

   private OrderLine example = new OrderLine();

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

   public OrderLine getExample()
   {
      return this.example;
   }

   public void setExample(OrderLine example)
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

   public List<OrderLine> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back OrderLine entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<OrderLine> getAll()
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

            return String.valueOf(((OrderLine) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private OrderLine add = new OrderLine();

   public OrderLine getAdd()
   {
      return this.add;
   }

   public OrderLine getAdded()
   {
      OrderLine added = this.add;
      this.add = new OrderLine();
      return added;
   }
}