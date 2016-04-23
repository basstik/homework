
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

    //http://localhost:8080/AmusementPark-web/park
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMachines(){
        List<Machine> machines = machineService.getMachines();
        return Response.ok().entity(new GenericEntity<List<Machine>>(machines) {}).build();
    }
    
    @PUT
    @Path("/addmachine")
    @Produces(MediaType.APPLICATION_JSON)
    public Machine addMachine(Machine machine){
        return machineService.addMachine(machine);
    }
    
        @DELETE
    @Path("/deletemachinek/{machineid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer deleteMachine(@PathParam("machineid")Long idOfMachine){
        return machineService.deleteMachine(idOfMachine);
    }
    
    
    @POST
    @Path("/updatemachine/{parkid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer updateMachine(@PathParam("parkid")Long idOfMachine, Machine machine){
        return machineService.updateMachine(idOfMachine, machine);
    }

}
