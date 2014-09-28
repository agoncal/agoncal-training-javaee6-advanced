package org.agoncal.training.javaee6adv.rest;

import org.agoncal.training.javaee6adv.model.PurchaseOrder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

/**
 *
 */
@Stateless
@Path("/purchaseorders")
public class PurchaseOrderEndpoint
{
   @PersistenceContext(unitName = "cdbookstorePU")
   private EntityManager em;

   @POST
   @Consumes({"application/xml", "application/json"})
   public Response create(PurchaseOrder entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(PurchaseOrderEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      PurchaseOrder entity = em.find(PurchaseOrder.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces({"application/xml", "application/json"})
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<PurchaseOrder> findByIdQuery = em.createQuery("SELECT DISTINCT p FROM PurchaseOrder p LEFT JOIN FETCH p.customer LEFT JOIN FETCH p.orderLines WHERE p.id = :entityId ORDER BY p.id", PurchaseOrder.class);
      findByIdQuery.setParameter("entityId", id);
      PurchaseOrder entity;
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
   @Produces({"application/xml", "application/json"})
   public List<PurchaseOrder> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<PurchaseOrder> findAllQuery = em.createQuery("SELECT DISTINCT p FROM PurchaseOrder p LEFT JOIN FETCH p.customer LEFT JOIN FETCH p.orderLines ORDER BY p.id", PurchaseOrder.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<PurchaseOrder> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes({"application/xml", "application/json"})
   public Response update(PurchaseOrder entity)
   {
      try
      {
         entity = em.merge(entity);
      }
      catch (OptimisticLockException e)
      {
         return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
      }

      return Response.noContent().build();
   }
}