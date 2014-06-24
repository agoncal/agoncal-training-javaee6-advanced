package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Category;

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
public class CategoryService extends AbstractService<Category> implements Serializable
{

    public CategoryService() {
        super(Category.class);
    }

   @Override
   protected Predicate[] getSearchPredicates(Root<Category> root, Category example)
   {
      CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String name = example.getName();
      if (name != null && !"".equals(name))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("name")), '%' + name.toLowerCase() + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }
}