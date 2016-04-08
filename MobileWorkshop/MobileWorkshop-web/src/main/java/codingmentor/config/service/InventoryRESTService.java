package codingmentor.config.service;

import codingmentor.dto.MobileDTO;
import codingmentor.interceptor.BeanValidation;
import codingmentor.service.InventoryService;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mobiles")
@Produces(MediaType.APPLICATION_JSON)
//@Stateless        //Nincs rá szükség, by László
//@BeanValidation
public class InventoryRESTService {

    @Inject
    //@EJB
    private InventoryService inventoryService;
    
    @GET
    public Collection<MobileDTO> getMobiles(){
        return inventoryService.getMobilesList();
    }
    
    @POST
    public MobileDTO addMobile(MobileDTO mobile){
        inventoryService.addMobile(mobile);
        return mobile;
    }
    

    
}
