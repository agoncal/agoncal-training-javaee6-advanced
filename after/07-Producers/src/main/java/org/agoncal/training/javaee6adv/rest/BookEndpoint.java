package org.agoncal.training.javaee6adv.rest;

import org.agoncal.training.javaee6adv.model.Book;
import org.agoncal.training.javaee6adv.service.BookService;

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
@Path("/books")
public class BookEndpoint
{
   @Inject
   private BookService service;

   @POST
   @Consumes({"application/xml", "application/json"})
   public Response create(Book entity)
   {
      entity = service.persist(entity);
      return Response.created(UriBuilder.fromResource(BookEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Book entity = service.findById(id);
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
      Book entity = service.findByIdWithRelations(id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces({"application/xml", "application/json"})
   public List<Book> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      return service.listAllWithRelations(startPosition, maxResult);
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes({"application/xml", "application/json"})
   public Response update(Book entity)
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