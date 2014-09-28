package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.model.Language;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class AuthorService extends AbstractService<Author> implements Serializable
{

   public AuthorService()
   {
      super(Author.class);
   }

   @Override
   protected Predicate[] getSearchPredicates(Root<Author> root, Author example)
   {

      CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String firstName = example.getFirstName();
      if (firstName != null && !"".equals(firstName))
      {
         predicatesList.add(builder.like(builder.lower(root.<String>get("firstName")), '%' + firstName.toLowerCase() + '%'));
      }
      String lastName = example.getLastName();
      if (lastName != null && !"".equals(lastName))
      {
         predicatesList.add(builder.like(builder.lower(root.<String>get("lastName")), '%' + lastName.toLowerCase() + '%'));
      }
      String bio = example.getBio();
      if (bio != null && !"".equals(bio))
      {
         predicatesList.add(builder.like(builder.lower(root.<String>get("bio")), '%' + bio.toLowerCase() + '%'));
      }
      Integer age = example.getAge();
      if (age != null && age.intValue() != 0)
      {
         predicatesList.add(builder.equal(root.get("age"), age));
      }
      Language preferredLanguage = example.getPreferredLanguage();
      if (preferredLanguage != null)
      {
         predicatesList.add(builder.equal(root.get("preferredLanguage"), preferredLanguage));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }
}