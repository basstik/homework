
package xyz.codingmentor.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.entities.Park;
import xyz.codingmentor.service.ParkService;


@Path("/park")
@Consumes(MediaType.APPLICATION_JSON)
public class ParkREST {
    
    @Inject
    ParkService parkService;

    //http://localhost:8080/AmusementPark-web/park
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParks(){
        List<Park> parks = parkService.getParks();
        return Response.ok().entity(new GenericEntity<List<Park>>(parks) {}).build();
    }
    
    @PUT
    @Path("/addpark")
    @Produces(MediaType.APPLICATION_JSON)
    public Park addPark(Park park){
        return parkService.addPark(park);
    }
    
    @POST
    @Path("/{parkid}/addmachine/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer addMachineToPark(@PathParam("parkid")Long idOfPark, 
            @PathParam("machineid")Long idOfMachine){
        return parkService.addMachineToPark(idOfPark, idOfMachine);
    }
    
    @POST
    @Path("/{parkid}/removemachine/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer removeMachineFromPark(@PathParam("parkid")Long idOfPark, 
            @PathParam("machineid")Long idOfMachine){
        return parkService.deleteMachineFromPark(idOfPark, idOfMachine);
    }
    
    @DELETE
    @Path("/deletepark/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer deletePark(@PathParam("parkid")Long idOfPark){
        return parkService.deletePark(idOfPark);
    }
    
    
    @POST
    @Path("/updatepark/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer updatePark(@PathParam("parkid")Long idOfPark, Park park){
        return parkService.updatePark(idOfPark, park);
    }
    
}
