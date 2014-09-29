package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Item;

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
public class ItemService extends AbstractService<Item> implements Serializable
{

   public ItemService()
   {
      super(Item.class);
   }

   @Override
   protected Predicate[] getSearchPredicates(Root<Item> root, Item example)
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

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }
}