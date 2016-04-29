package xyz.codingmentor.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.entities.Visitor;
import xyz.codingmentor.service.VisitorService;

@Path("/visitor")
@Consumes(MediaType.APPLICATION_JSON)
public class VisitorREST {

    @Inject
    VisitorService visitorService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVisitors() {
        List<Visitor> visitors = visitorService.getVisitors();
        return Response.ok().entity(new GenericEntity<List<Visitor>>(visitors) {
        }).build();
    }
    
    @GET
    @Path("/{visitorid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Visitor getVisitor(@PathParam("visitorid") Long idOfVisitor) {
        return visitorService.getVisitorById(idOfVisitor);
    }
    

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addVisitor(Visitor visitor) {
        visitorService.addVisitor(visitor);
        return Response.status(Response.Status.CREATED).entity("Success").build();

    }

    @PUT
    @Path("/logintopark/{visitorid}/park/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginToPark(@PathParam("visitorid") Long idOfVisitor,
            @PathParam("parkid") Long idOfPark) {
        visitorService.loginToPark(idOfVisitor, idOfPark);
        return Response.status(Response.Status.OK).entity("Success login").build();
    }

    @PUT
    @Path("/exitfrompark/{visitorid}/park/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response exitToPark(@PathParam("visitorid") Long idOfVisitor,
            @PathParam("parkid") Long idOfPark) {
        visitorService.exitFromPark(idOfVisitor, idOfPark);
        return Response.status(Response.Status.OK).entity("Success exit").build();

    }

    @PUT
    @Path("/boardtomachine/{visitorid}/machine/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response boardToMachine(@PathParam("visitorid") Long idOfVisitor,
            @PathParam("machineid") Long idOfMachine) {
        visitorService.boardToMachine(idOfVisitor, idOfMachine);
        return Response.status(Response.Status.OK).entity("Success board to machine").build();

    }

    @PUT
    @Path("/unboardtomachine/{visitorid}/machine/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response unBoardToMachine(@PathParam("visitorid") Long idOfVisitor,
            @PathParam("machineid") Long idOfMachine) {
        visitorService.unBoardFromMachine(idOfVisitor, idOfMachine);
        return Response.status(Response.Status.OK).entity("Success unboard to machine").build();

    }

    @PUT
    @Path("/writetobook/{visitorid}/park/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response writeToGuestBook(@PathParam("visitorid") Long idOfVisitor,
            @PathParam("parkid") Long idOfPark, String message) {
        visitorService.writeToGuestBook(idOfVisitor, idOfPark, message);
        return Response.status(Response.Status.OK).entity("Success write to guestbook").build();

    }

    @DELETE
    @Path("/deletevisitor/{visitorid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteVisitor(@PathParam("visitorid") Long idOfVisitor) {
        visitorService.deleteVisitor(idOfVisitor);
        return Response.status(Response.Status.OK).entity("Success delete visitor").build();

    }

    @PUT
    @Path("/updatevisitor/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateVisitor(@PathParam("parkid") Long idOfVisitor, Visitor visitor) {
        visitorService.updateVisitor(idOfVisitor, visitor);
        return Response.status(Response.Status.OK).entity("Success update visitor").build();

    }

}
