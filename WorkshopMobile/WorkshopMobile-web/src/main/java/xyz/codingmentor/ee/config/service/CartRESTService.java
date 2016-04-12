package xyz.codingmentor.ee.config.service;


import java.io.Serializable;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import xyz.codingmentor.ee.dto.MobileDTO;
import xyz.codingmentor.ee.interceptor.BeanValidation;
import xyz.codingmentor.ee.service.CartService;
import xyz.codingmentor.ee.service.InventoryService;


@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@BeanValidation
public class CartRESTService implements Serializable{
    
    @Inject
    private CartService cartService;
  
    @Inject
    private InventoryService inventoryService;
       

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer addToCart(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(200);
        Object username = session.getAttribute("username");

        if (username != null && username instanceof String) {
            if(inventoryService.mobileIsPurchasable(mobile)){
                 cartService.addToCart(mobile);
                 return 1;
            }
        }
        return -1;
    }
 
    
    @POST
    @Path("/checkout")
    @Produces(MediaType.TEXT_HTML)
    public Integer checkout(@Context HttpServletRequest request) {
        cartService.checkout();
        request.getSession().invalidate();
        return 1;
    }
}
