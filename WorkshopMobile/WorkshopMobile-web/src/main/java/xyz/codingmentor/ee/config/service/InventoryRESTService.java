package xyz.codingmentor.ee.config.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import xyz.codingmentor.ee.dto.MobileDTO;
import xyz.codingmentor.ee.interceptor.BeanValidation;
import xyz.codingmentor.ee.service.InventoryService;

@Path("/mobiles")
@Consumes(MediaType.APPLICATION_JSON)
@BeanValidation
public class InventoryRESTService {

    @Inject
    private InventoryService inventoryService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<MobileDTO> getMobiles() {
        return inventoryService.getMobilesList();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public MobileDTO addMobile(MobileDTO mobile) {
        inventoryService.addMobile(mobile);
        return mobile;
    }

}
