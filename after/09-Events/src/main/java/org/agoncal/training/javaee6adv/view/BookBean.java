package org.agoncal.training.javaee6adv.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.agoncal.training.javaee6adv.model.Book;
import org.agoncal.training.javaee6adv.service.BookService;

/**
 * Backing bean for Book entities.
 * <p>
 * This class provides CRUD functionality for all Book entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@ConversationScoped
public class BookBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Book entities
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

   private Book book;

   public Book getBook()
   {
      return this.book;
   }

   public void setBook(Book book)
   {
      this.book = book;
   }

   @Inject
   private Conversation conversation;

   @Inject
   private BookService service;

   @Inject
   private Event<Book> boughtBook;

   public String buy()
   {
      Book book = service.findById(id);

      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage("Successful",  "You've bought " + book.getTitle()) );

      boughtBook.fire(book);

      return null;
   }

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
         this.book = this.example;
      }
      else
      {
         this.book = findById(getId());
      }
   }

   public Book findById(Long id)
   {

      return this.service.findByIdWithRelations(id);
   }

   /*
    * Support updating and deleting Book entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.service.persist(this.book);
            return "search?faces-redirect=true";
         }
         else
         {
            this.service.merge(this.book);
            return "view?faces-redirect=true&id=" + this.book.getId();
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
         Book deletableEntity = findById(getId());

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
    * Support searching Book entities with pagination
    */

   private int page;
   private long count;
   private List<Book> pageItems;

   private Book example = new Book();

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

   public Book getExample()
   {
      return this.example;
   }

   public void setExample(Book example)
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

   public List<Book> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Book entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Book> getAll()
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

            return String.valueOf(((Book) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Book add = new Book();

   public Book getAdd()
   {
      return this.add;
   }

   public Book getAdded()
   {
      Book added = this.add;
      this.add = new Book();
      return added;
   }

   /*
    * Support for having books per category
    */

   private Long categoryId;

   public Long getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
   }

   private List<Book> booksPerCateogry;

   public List<Book> getBooksPerCateogry() {
      return booksPerCateogry;
   }

   public void setBooksPerCateogry(List<Book> booksPerCateogry) {
      this.booksPerCateogry = booksPerCateogry;
   }

   public void findByCategory()
   {
      this.booksPerCateogry = this.service.findByCategory(categoryId);
   }
}