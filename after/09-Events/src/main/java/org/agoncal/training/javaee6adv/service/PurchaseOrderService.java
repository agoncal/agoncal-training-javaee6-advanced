package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.CD;
import org.agoncal.training.javaee6adv.model.Customer;
import org.agoncal.training.javaee6adv.model.Item;
import org.agoncal.training.javaee6adv.model.OrderLine;
import org.agoncal.training.javaee6adv.model.PurchaseOrder;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
@LocalBean
public class PurchaseOrderService extends AbstractService<PurchaseOrder> implements Serializable
{
   @Inject
   private RandomService randomService;

   public PurchaseOrderService() {
      super(PurchaseOrder.class);
   }

   public void itemHasBeenBought(@Observes Item itemBought){

      OrderLine orderLine = new OrderLine();
      orderLine.setItem(getEntityManager().merge(itemBought));
      orderLine.setQuantity(1);

      Set<OrderLine> orderLines = new HashSet<>();
      orderLines.add(orderLine);

      PurchaseOrder purchaseOrder = new PurchaseOrder();
      purchaseOrder.setOrderLines(orderLines);

      purchaseOrder.setCustomer(randomService.getCustomer());
      purchaseOrder.setDeliveryAddress(randomService.getAddress());
      purchaseOrder.setCreditCard(randomService.getCreditCard());

      getEntityManager().persist(purchaseOrder);
   }

   @Override
   protected Predicate[] getSearchPredicates(Root<PurchaseOrder> root, PurchaseOrder example) 
   {
      CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int quantity = example.getQuantity();
      if (quantity != 0)
      {
         predicatesList.add(builder.equal(root.get("quantity"), quantity));
      }
      Customer customer = example.getCustomer();
      if (customer != null)
      {
         predicatesList.add(builder.equal(root.get("customer"), customer));
      }
      String street1 = example.getStreet1();
      if (street1 != null && !"".equals(street1))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("street1")), '%' + street1.toLowerCase() + '%'));
      }
      String street2 = example.getStreet2();
      if (street2 != null && !"".equals(street2))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("street2")), '%' + street2.toLowerCase() + '%'));
      }
      String city = example.getCity();
      if (city != null && !"".equals(city))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("city")), '%' + city.toLowerCase() + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }
}