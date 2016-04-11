package xyz.codingmentor.ee.config.service;

import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
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
import xyz.codingmentor.ee.exception.IdNotMatchException;
import xyz.codingmentor.ee.interceptor.BeanValidation;
import xyz.codingmentor.ee.service.UserManagementService;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@BeanValidation
public class UserRESTService implements Serializable{

    @Inject
    private UserManagementService userMgmtService;
    
    //http://localhost:8080/WorkshopMobile-web/mobileworkshop/users
    @GET
    @Path("/")
    public Collection<UserDTO> getUsers() {
        return userMgmtService.getUsers();
    }
    
    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam("username") String username) {
        return userMgmtService.getUser(username);
    }
    
    @POST
    @Path("/")
    public UserDTO addUser(UserDTO user) {
        return userMgmtService.addUser(user);
    }
    
    @DELETE
    @Path("/{username}")
    public UserDTO deleteUser(@PathParam("username") String username) {
       return userMgmtService.removeUser(username);
    }
  
   
    @PUT
    @Path("/")
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
                session.setAttribute("username", acceptedUser);
                System.out.println(session.getId());
                return user;
            }
        }
        throw new IllegalArgumentException("Isn't this username");
    }
    
    @POST
    @Path("/logout")
    public Response logout(@Context HttpServletRequest request){
        //HttpSession session = request.getSession(true);
        //System.out.println(session.getId());
        //session.invalidate();
       // return "success logout";
        return Response.ok("success logout").build();
    }
}

