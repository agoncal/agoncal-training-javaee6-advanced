package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.MajorLabel;

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
public class MajorLabelService extends AbstractService<MajorLabel> implements Serializable
{

    public MajorLabelService() {
        super(MajorLabel.class);
    }

   @Override
   protected Predicate[] getSearchPredicates(Root<MajorLabel> root, MajorLabel example)
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