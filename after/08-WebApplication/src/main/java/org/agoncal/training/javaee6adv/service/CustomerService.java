package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Customer;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class CustomerService extends AbstractService<Customer> implements Serializable
{

   public CustomerService() {
      super(Customer.class);
   }

   @Override
   protected Predicate[] getSearchPredicates(Root<Customer> root, Customer example)
   {
      CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String firstname = example.getFirstname();
      if (firstname != null && !"".equals(firstname))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("firstname")), '%' + firstname.toLowerCase() + '%'));
      }
      String lastname = example.getLastname();
      if (lastname != null && !"".equals(lastname))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("lastname")), '%' + lastname.toLowerCase() + '%'));
      }
      String telephone = example.getTelephone();
      if (telephone != null && !"".equals(telephone))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("telephone")), '%' + telephone.toLowerCase() + '%'));
      }
      String email = example.getEmail();
      if (email != null && !"".equals(email))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("email")), '%' + email.toLowerCase() + '%'));
      }
      int age = example.getAge();
      if (age != 0)
      {
         predicatesList.add(builder.equal(root.get("age"), age));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }
}