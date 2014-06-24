package org.agoncal.training.javaee6adv.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.agoncal.training.javaee6adv.model.CD;
import org.agoncal.training.javaee6adv.service.CDService;

/**
 * 
 */
@Path("/cds")
public class CDEndpoint
{
   @Inject
   private CDService service;

   @POST
   @Consumes({"application/xml","application/json"})
   public Response create(CD entity)
   {
      entity = service.persist(entity);
      return Response.created(UriBuilder.fromResource(CDEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      CD entity = service.findById(id);
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
      CD entity = service.findByIdWithRelations(id);
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
      return service.listAllWithRelations(startPosition, maxResult);
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes({"application/xml","application/json"})
   public Response update(CD entity)
   {
      entity = service.merge(entity);
      return Response.noContent().build();
   }
}