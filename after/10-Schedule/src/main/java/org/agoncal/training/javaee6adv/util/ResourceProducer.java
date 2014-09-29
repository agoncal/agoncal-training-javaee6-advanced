package org.agoncal.training.javaee6adv.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

public class ResourceProducer
{
   @Produces
   @PersistenceContext(unitName = "cdbookstorePU")
   private EntityManager em;

   @Produces
   @RequestScoped
   private FacesContext produceFacesContext()
   {
      return FacesContext.getCurrentInstance();
   }

   @Produces
   private Logger produceLogger(InjectionPoint injectionPoint)
   {
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }
}