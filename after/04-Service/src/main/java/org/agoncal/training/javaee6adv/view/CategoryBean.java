package org.agoncal.training.javaee6adv.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
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

import org.agoncal.training.javaee6adv.model.Category;
import org.agoncal.training.javaee6adv.service.CategoryService;

/**
 * Backing bean for Category entities.
 * <p>
 * This class provides CRUD functionality for all Category entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@ConversationScoped
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Category entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Category category;

	public Category getCategory() {
		return this.category;
	}

	@Inject
	private Conversation conversation;

	@Inject
	private CategoryService service;

	public String create() {

		this.conversation.begin();
		return "create?faces-redirect=true";
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
		}

		if (this.id == null) {
			this.category = this.example;
		} else {
			this.category = findById(getId());
		}
	}

	public Category findById(Long id) {

		return service.findById(id);
	}

	/*
	 * Support updating and deleting Category entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				service.persist(this.category);
				return "search?faces-redirect=true";
			} else {
				service.merge(this.category);
				return "view?faces-redirect=true&id=" + this.category.getId();
			}
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
		    Category deletableEntity = findById(getId());
            
			this.service.remove(deletableEntity);
			return "search?faces-redirect=true";
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
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

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Category getExample() {
		return this.example;
	}

	public void setExample(Category example) {
		this.example = example;
	}

	public void search() {
		this.page = 0;
	}

	public void paginate() {

        // Populate this.count
        this.count = service.count(example);

        // Populate this.pageItems
        this.pageItems = service.page(example, page, getPageSize());
	}

	public List<Category> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Category entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Category> getAll() {

		return service.listAll();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return service.findById(Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
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

	public Category getAdd() {
		return this.add;
	}

	public Category getAdded() {
		Category added = this.add;
		this.add = new Category();
		return added;
	}
}