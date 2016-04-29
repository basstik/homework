package xyz.codingmentor.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.entities.Machine;
import xyz.codingmentor.service.MachineService;

@Path("/machine")
@Consumes(MediaType.APPLICATION_JSON)
public class MachineREST {

    @Inject
    MachineService machineService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMachines() {
        List<Machine> machines = machineService.getMachines();
        return Response.ok().entity(new GenericEntity<List<Machine>>(machines) {
        }).build();
    }

    @GET
    @Path("/{machineid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Machine getMacine(@PathParam("machineid") Long idOfMachine) {
        return machineService.getMachineById(idOfMachine);
    }

    @PUT
    @Path("/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMachine(@PathParam("machineid") Long idOfMachine, Machine machine) {
        machineService.updateMachine(idOfMachine, machine);
        return Response.status(Response.Status.OK).entity("Success update machine").build();

    }
    
    @DELETE
    @Path("/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMachine(@PathParam("machineid") Long idOfMachine) {
        machineService.deleteMachine(idOfMachine);
        return Response.status(Response.Status.OK).entity("Success delete machine").build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addMachine(Machine machine) {
        machineService.addMachine(machine);
        return Response.status(Response.Status.CREATED).entity("Success").build();
    }


}
