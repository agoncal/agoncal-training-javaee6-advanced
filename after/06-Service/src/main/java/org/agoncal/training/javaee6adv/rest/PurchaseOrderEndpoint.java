package org.agoncal.training.javaee6adv.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.agoncal.training.javaee6adv.model.PurchaseOrder;
import org.agoncal.training.javaee6adv.service.PurchaseOrderService;

/**
 * 
 */
@Path("/purchaseorders")
public class PurchaseOrderEndpoint
{
   @Inject
   private PurchaseOrderService service;

   @POST
   @Consumes({"application/xml","application/json"})
   public Response create(PurchaseOrder entity)
   {
      entity = service.persist(entity);
      return Response.created(UriBuilder.fromResource(PurchaseOrderEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      PurchaseOrder entity = service.findById(id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      service.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces({"application/xml","application/json"})
   public Response findById(@PathParam("id") Long id)
   {
      PurchaseOrder entity = service.findById(id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces({"application/xml","application/json"})
   public List<PurchaseOrder> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      return service.listAll(startPosition, maxResult);
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes({"application/xml","application/json"})
   public Response update(PurchaseOrder entity)
   {
      entity = service.merge(entity);
      return Response.noContent().build();
   }
}