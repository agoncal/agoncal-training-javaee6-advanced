package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Book;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class BookService extends AbstractService<Book> implements Serializable
{

   public BookService()
   {
      super(Book.class);
   }

   public Book findByIdWithRelations(Long id)
   {
      TypedQuery<Book> findByIdQuery = getEntityManager().createQuery("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.category LEFT JOIN FETCH b.author LEFT JOIN FETCH b.publisher WHERE b.id = :entityId ORDER BY b.id", Book.class);
      findByIdQuery.setParameter("entityId", id);
      Book entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      return entity;
   }

   public List<Book> listAllWithRelations(Integer startPosition, Integer maxResult)
   {
      TypedQuery<Book> findAllQuery = getEntityManager().createQuery("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.category LEFT JOIN FETCH b.author LEFT JOIN FETCH b.publisher ORDER BY b.id", Book.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<Book> results = findAllQuery.getResultList();
      return results;
   }

   @Override
   protected Predicate[] getSearchPredicates(Root<Book> root, Book example)
   {
      CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String title = example.getTitle();
      if (title != null && !"".equals(title))
      {
         predicatesList.add(builder.like(builder.lower(root.<String>get("title")), '%' + title.toLowerCase() + '%'));
      }
      String description = example.getDescription();
      if (description != null && !"".equals(description))
      {
         predicatesList.add(builder.like(builder.lower(root.<String>get("description")), '%' + description.toLowerCase() + '%'));
      }
      String imageURL = example.getImageURL();
      if (imageURL != null && !"".equals(imageURL))
      {
         predicatesList.add(builder.like(builder.lower(root.<String>get("imageURL")), '%' + imageURL.toLowerCase() + '%'));
      }
      String isbn = example.getIsbn();
      if (isbn != null && !"".equals(isbn))
      {
         predicatesList.add(builder.like(builder.lower(root.<String>get("isbn")), '%' + isbn.toLowerCase() + '%'));
      }
      Integer nbOfPages = example.getNbOfPages();
      if (nbOfPages != null && nbOfPages.intValue() != 0)
      {
         predicatesList.add(builder.equal(root.get("nbOfPages"), nbOfPages));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }
}