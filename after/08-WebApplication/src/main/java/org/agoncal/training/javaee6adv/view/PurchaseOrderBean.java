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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.agoncal.training.javaee6adv.model.PurchaseOrder;
import org.agoncal.training.javaee6adv.model.Customer;
import org.agoncal.training.javaee6adv.service.PurchaseOrderService;

/**
 * Backing bean for PurchaseOrder entities.
 * <p>
 * This class provides CRUD functionality for all PurchaseOrder entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@ConversationScoped
public class PurchaseOrderBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving PurchaseOrder entities
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

   private PurchaseOrder purchaseOrder;

   public PurchaseOrder getPurchaseOrder()
   {
      return this.purchaseOrder;
   }

   public void setPurchaseOrder(PurchaseOrder purchaseOrder)
   {
      this.purchaseOrder = purchaseOrder;
   }

   @Inject
   private Conversation conversation;

   @Inject
   private PurchaseOrderService service;

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
         this.purchaseOrder = this.example;
      }
      else
      {
         this.purchaseOrder = findById(getId());
      }
   }

   public PurchaseOrder findById(Long id)
   {

      return this.service.findById(id);
   }

   /*
    * Support updating and deleting PurchaseOrder entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.service.persist(this.purchaseOrder);
            return "search?faces-redirect=true";
         }
         else
         {
            this.service.merge(this.purchaseOrder);
            return "view?faces-redirect=true&id=" + this.purchaseOrder.getId();
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
         PurchaseOrder deletableEntity = findById(getId());

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
    * Support searching PurchaseOrder entities with pagination
    */

   private int page;
   private long count;
   private List<PurchaseOrder> pageItems;

   private PurchaseOrder example = new PurchaseOrder();

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

   public PurchaseOrder getExample()
   {
      return this.example;
   }

   public void setExample(PurchaseOrder example)
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

   public List<PurchaseOrder> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back PurchaseOrder entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<PurchaseOrder> getAll()
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

            return String.valueOf(((PurchaseOrder) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private PurchaseOrder add = new PurchaseOrder();

   public PurchaseOrder getAdd()
   {
      return this.add;
   }

   public PurchaseOrder getAdded()
   {
      PurchaseOrder added = this.add;
      this.add = new PurchaseOrder();
      return added;
   }
}