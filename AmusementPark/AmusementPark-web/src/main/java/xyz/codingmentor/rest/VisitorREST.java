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

    @PUT
    @Path("/addvisitor")
    @Produces(MediaType.APPLICATION_JSON)
    public Visitor addVisitor(Visitor visitor) {
        return visitorService.addVisitor(visitor);
    }

    @POST
    @Path("/logintopark/{visitorid}/park/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer loginToPark(@PathParam("visitorid")Long idOfVisitor,
            @PathParam("parkid")Long idOfPark) {
        visitorService.loginToPark(idOfVisitor,idOfPark);
        return 1;
    }
    
    @POST
    @Path("/exitfrompark/{visitorid}/park/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer exitToPark(@PathParam("visitorid")Long idOfVisitor,
            @PathParam("parkid")Long idOfPark) {
        visitorService.exitFromPark(idOfVisitor,idOfPark);
        return 1;
    }
    
    @POST
    @Path("/boardtomachine/{visitorid}/machine/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer boardToMachine(@PathParam("visitorid")Long idOfVisitor,
            @PathParam("machineid")Long idOfMachine) {
        visitorService.boardToMachine(idOfVisitor,idOfMachine);
        return 1;
    }
    
    @POST
    @Path("/unboardtomachine/{visitorid}/machine/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer unBoardToMachine(@PathParam("visitorid")Long idOfVisitor,
            @PathParam("machineid")Long idOfMachine) {
        visitorService.unBoardFromMachine(idOfVisitor,idOfMachine);
        return 1;
    }
    
    @POST
    @Path("/writetobook/{visitorid}/park/{parkid}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String writeToGuestBook(@PathParam("visitorid")Long idOfVisitor,
            @PathParam("parkid")Long idOfPark, String message) {
        return visitorService.writeToGuestBook(idOfVisitor,idOfPark,message);
    }

    @DELETE
    @Path("/deletevisitork/{visitorid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer deleteVisitor(@PathParam("visitorid")Long idOfVisitor){
        return visitorService.deleteVisitor(idOfVisitor);
    }
    
    
    @POST
    @Path("/updatevisitor/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer updateVisitor(@PathParam("parkid")Long idOfVisitor, Visitor visitor){
        return visitorService.updateVisitor(idOfVisitor, visitor);
    }

}
