package org.agoncal.training.javaee6adv.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.agoncal.training.javaee6adv.model.Customer;

/**
 * 
 */
@Stateless
@Path("/customers")
public class CustomerEndpoint
{
   @PersistenceContext(unitName = "cdbookstorePU")
   private EntityManager em;

   @POST
   @Consumes("application/xml")
   public Response create(Customer entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(CustomerEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Customer entity = em.find(Customer.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/xml")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<Customer> findByIdQuery = em.createQuery("SELECT DISTINCT c FROM Customer c WHERE c.id = :entityId ORDER BY c.id", Customer.class);
      findByIdQuery.setParameter("entityId", id);
      Customer entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/xml")
   public List<Customer> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<Customer> findAllQuery = em.createQuery("SELECT DISTINCT c FROM Customer c ORDER BY c.id", Customer.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<Customer> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/xml")
   public Response update(Customer entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}