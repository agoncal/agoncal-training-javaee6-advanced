package org.agoncal.training.javaee6adv.rest;

import org.agoncal.training.javaee6adv.model.Author;
import org.agoncal.training.javaee6adv.service.AuthorService;

import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
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
@Path("/authors")
public class AuthorEndpoint
{
   @Inject
   private AuthorService service;

   @POST
   @Consumes({"application/xml", "application/json"})
   public Response create(Author entity)
   {
      entity = service.persist(entity);
      return Response.created(UriBuilder.fromResource(AuthorEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Author entity = service.findById(id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      service.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces({"application/xml", "application/json"})
   public Response findById(@PathParam("id") Long id)
   {
      Author entity = service.findById(id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces({"application/xml", "application/json"})
   public List<Author> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      return service.listAll(startPosition, maxResult);
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes({"application/xml", "application/json"})
   public Response update(Author entity)
   {
      try
      {
         entity = service.merge(entity);
      }
      catch (OptimisticLockException e)
      {
         return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
      }

      return Response.noContent().build();
   }
}