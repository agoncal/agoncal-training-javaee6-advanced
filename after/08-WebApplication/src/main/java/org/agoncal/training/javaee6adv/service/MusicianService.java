package org.agoncal.training.javaee6adv.service;


import org.agoncal.training.javaee6adv.model.Musician;

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
public class MusicianService extends AbstractService<Musician> implements Serializable
{

   public MusicianService()
   {
      super(Musician.class);
   }

   @Override
   protected Predicate[] getSearchPredicates(Root<Musician> root, Musician example)
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
      String preferredInstrument = example.getPreferredInstrument();
      if (preferredInstrument != null && !"".equals(preferredInstrument))
      {
         predicatesList.add(builder.like(builder.lower(root.<String>get("preferredInstrument")), '%' + preferredInstrument.toLowerCase() + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }
}