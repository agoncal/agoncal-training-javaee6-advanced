package org.agoncal.training.javaee6adv.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.agoncal.training.javaee6adv.model.MajorLabel;

/**
 * Backing bean for MajorLabel entities.
 * <p>
 * This class provides CRUD functionality for all MajorLabel entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class MajorLabelBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving MajorLabel entities
    */

   private java.lang.Long id;

   public java.lang.Long getId()
   {
      return this.id;
   }

   public void setId(java.lang.Long id)
   {
      this.id = id;
   }

   private MajorLabel majorLabel;

   public MajorLabel getMajorLabel()
   {
      return this.majorLabel;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(unitName = "cdbookstorePU", type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

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
         this.majorLabel = this.example;
      }
      else
      {
         this.majorLabel = findById(getId());
      }
   }

   public MajorLabel findById(java.lang.Long id)
   {

      return this.entityManager.find(MajorLabel.class, id);
   }

   /*
    * Support updating and deleting MajorLabel entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.majorLabel);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.majorLabel);
            return "view?faces-redirect=true&id=" + this.majorLabel.getId();
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
         MajorLabel deletableEntity = findById(getId());

         this.entityManager.remove(deletableEntity);
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching MajorLabel entities with pagination
    */

   private int page;
   private long count;
   private List<MajorLabel> pageItems;

   private MajorLabel example = new MajorLabel();

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

   public MajorLabel getExample()
   {
      return this.example;
   }

   public void setExample(MajorLabel example)
   {
      this.example = example;
   }

   public void search()
   {
      this.page = 0;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<MajorLabel> root = countCriteria.from(MajorLabel.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<MajorLabel> criteria = builder.createQuery(MajorLabel.class);
      root = criteria.from(MajorLabel.class);
      TypedQuery<MajorLabel> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<MajorLabel> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String name = this.example.getName();
      if (name != null && !"".equals(name))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("name")), '%' + name.toLowerCase() + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<MajorLabel> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back MajorLabel entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<MajorLabel> getAll()
   {

      CriteriaQuery<MajorLabel> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(MajorLabel.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(MajorLabel.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final MajorLabelBean ejbProxy = this.sessionContext.getBusinessObject(MajorLabelBean.class);

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(java.lang.Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((MajorLabel) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private MajorLabel add = new MajorLabel();

   public MajorLabel getAdd()
   {
      return this.add;
   }

   public MajorLabel getAdded()
   {
      MajorLabel added = this.add;
      this.add = new MajorLabel();
      return added;
   }
}