package xyz.codingmentor.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.entities.Machine;
import xyz.codingmentor.entities.Park;
import xyz.codingmentor.service.ParkService;

@Path("/park")
@Consumes(MediaType.APPLICATION_JSON)
public class ParkREST {

    @Inject
    ParkService parkService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParks() {
        List<Park> parks = parkService.getParks();
        return Response.ok().entity(new GenericEntity<List<Park>>(parks) {
        }).build();
    }

    @GET
    @Path("/{parkid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Park getPark(@PathParam("parkid") Long idOfPark) {
        return parkService.getParkById(idOfPark);
    }

    @PUT
    @Path("/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePark(@PathParam("parkid") Long idOfPark, Park park) {
        parkService.updatePark(idOfPark, park);
        return Response.status(Response.Status.OK).entity("Success update park").build();

    }

    @DELETE
    @Path("/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePark(@PathParam("parkid") Long idOfPark) {
        parkService.deletePark(idOfPark);
        return Response.status(Response.Status.OK).entity("Success delete park").build();

    }

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addPark(Park park) {
        parkService.addPark(park);
        return Response.status(Response.Status.CREATED).entity("Success").build();

    }

    @PUT
    @Path("/{parkid}/addmachine/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addMachineToPark(@PathParam("parkid") Long idOfPark,
            @PathParam("machineid") Long idOfMachine) {
        parkService.addMachineToPark(idOfPark, idOfMachine);
        return Response.status(Response.Status.OK).entity("Success machine to park").build();
    }

    @PUT
    @Path("/{parkid}/removemachine/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeMachineFromPark(@PathParam("parkid") Long idOfPark,
            @PathParam("machineid") Long idOfMachine) {
        parkService.deleteMachineFromPark(idOfPark, idOfMachine);
        return Response.status(Response.Status.OK).entity("Success remove machine from park").build();

    }

    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPark() {
        List<Park> parks = parkService.getParks();
        return Response.ok().entity(new GenericEntity<List<Park>>(parks) {
        }).build();
    }

    @GET
    @Path("/{parkid}/machines")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMachineListInPark(@PathParam("parkid") Long idOfPark) {
        List<Machine> machines = parkService.getMachineListInPark(idOfPark);
        return Response.ok().entity(new GenericEntity<List<Machine>>(machines){}).build();
    }
}
