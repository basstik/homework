package xyz.codingmentor.ee.config.service;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import xyz.codingmentor.ee.dto.MobileDTO;
import xyz.codingmentor.ee.dto.UserDTO;
import xyz.codingmentor.ee.interceptor.BeanValidation;
import xyz.codingmentor.ee.service.CartService;
import xyz.codingmentor.ee.service.InventoryService;


@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@SessionScoped
@BeanValidation
public class CartRESTService implements Serializable {
    
    @Inject
    private CartService cartService;
  
    @Inject
    private InventoryService inventoryService;

    public CartRESTService() {
    }
       
    
    @POST
    @Consumes("application/json")
    public MobileDTO addToCart(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(200);
        Object userObject = session.getAttribute("username");
        UserDTO user;
        if ((userObject != null) && (userObject instanceof UserDTO)) {
            user = (UserDTO) userObject;
            System.out.println(user.getUsername() + " add a new product to the cart");
        }
        
        //Verify, that the mobile is available in inventory
        //The number of available mobile will decrease by buy 
        inventoryService.mobileIsPurchasable(mobile);
        
        //add Mobile to cart
        cartService.addToCart(mobile);

        return mobile;
    }
    
    @POST
    @Path("/checkout")
    public void checkout(@Context HttpServletRequest request) {
        cartService.checkout();
        request.getSession().invalidate();
    }
}
