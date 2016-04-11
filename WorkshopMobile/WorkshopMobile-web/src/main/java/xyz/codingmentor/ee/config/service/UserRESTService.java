package xyz.codingmentor.ee.config.service;

import java.io.Serializable;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.ee.dto.UserDTO;
import xyz.codingmentor.ee.interceptor.BeanValidation;
import xyz.codingmentor.ee.service.UserManagementService;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@BeanValidation
public class UserRESTService implements Serializable{

    @Inject
    private UserManagementService userMgmtService;
    
    //http://localhost:8080/WorkshopMobile-web/mobileworkshop/users
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UserDTO> getUsers() {
        return userMgmtService.getUsers();
    }
    
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUser(@PathParam("username") String username) {
        return userMgmtService.getUser(username);
    }
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO addUser(UserDTO user) {
        return userMgmtService.addUser(user);
    }
    
    @DELETE
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO deleteUser(@PathParam("username") String username) {
       return userMgmtService.removeUser(username);
    }
  
   
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO editUser( UserDTO user) {
            return userMgmtService.editUser(user);
    }
    
    @POST
    @Path("/login")
    public UserDTO login(@Context HttpServletRequest request, 
            UserDTO user){
       
        for (UserDTO acceptedUser : userMgmtService.getUsers()) {
            if (acceptedUser.getUsername().equals(user.getUsername())&& 
                    acceptedUser.getPassword().equals(user.getPassword())) {
                HttpSession session;
                session = request.getSession(true);
                session.setMaxInactiveInterval(300);
                session.setAttribute("username", acceptedUser.getUsername());
                return user;
            }
        }
        throw new IllegalArgumentException("Isn't this username");
    }
    
    @POST
    @Path("/logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(@Context HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.invalidate();
        return Response.ok("success logout").build();
    }
}

