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
import org.agoncal.training.javaee6adv.model.CD;

/**
 * 
 */
@Stateless
@Path("/cds")
public class CDEndpoint
{
   @PersistenceContext(unitName = "cdbookstorePU")
   private EntityManager em;

   @POST
   @Consumes({"application/xml","application/json"})
   public Response create(CD entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(CDEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      CD entity = em.find(CD.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces({"application/xml","application/json"})
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<CD> findByIdQuery = em.createQuery("SELECT DISTINCT c FROM CD c LEFT JOIN FETCH c.label LEFT JOIN FETCH c.genre LEFT JOIN FETCH c.musicians WHERE c.id = :entityId ORDER BY c.id", CD.class);
      findByIdQuery.setParameter("entityId", id);
      CD entity;
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
   @Produces({"application/xml","application/json"})
   public List<CD> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<CD> findAllQuery = em.createQuery("SELECT DISTINCT c FROM CD c LEFT JOIN FETCH c.label LEFT JOIN FETCH c.genre LEFT JOIN FETCH c.musicians ORDER BY c.id", CD.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<CD> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes({"application/xml","application/json"})
   public Response update(CD entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}