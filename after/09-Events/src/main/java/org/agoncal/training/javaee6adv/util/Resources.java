package org.agoncal.training.javaee6adv.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources
{
    @Produces
    @PersistenceContext(unitName="cdbookstorePU")
    private EntityManager em;
}