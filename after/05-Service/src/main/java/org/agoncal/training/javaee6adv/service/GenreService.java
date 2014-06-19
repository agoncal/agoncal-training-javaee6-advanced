package org.agoncal.training.javaee6adv.service;


import org.agoncal.training.javaee6adv.model.Genre;

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
public class GenreService extends AbstractService<Genre> implements Serializable {

    public GenreService() {
        super(Genre.class);
    }

    protected Predicate[] getSearchPredicates(Root<Genre> root, Genre example) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        List<Predicate> predicatesList = new ArrayList<Predicate>();

        String name = example.getName();
        if (name != null && !"".equals(name)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("name")), '%' + name.toLowerCase() + '%'));
        }

        return predicatesList.toArray(new Predicate[predicatesList.size()]);
    }

}