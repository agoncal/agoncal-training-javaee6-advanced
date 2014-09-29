package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.OrderLine;

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
public class OrderLineService extends AbstractService<OrderLine> implements Serializable
{

   public OrderLineService()
   {
      super(OrderLine.class);
   }

   @Override
   protected Predicate[] getSearchPredicates(Root<OrderLine> root, OrderLine example)
   {
      CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int quantity = example.getQuantity();
      if (quantity != 0)
      {
         predicatesList.add(builder.equal(root.get("quantity"), quantity));
      }
      Item item = example.getItem();
      if (item != null)
      {
         predicatesList.add(builder.equal(root.get("item"), item));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }
}