package xyz.codingmentor.ee.config.service;

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
import xyz.codingmentor.ee.dto.UserDTO;
import xyz.codingmentor.ee.exception.IdNotMatchException;
import xyz.codingmentor.ee.interceptor.BeanValidation;
import xyz.codingmentor.ee.service.UserManagementService;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@BeanValidation
public class UserRESTService {

    @Inject
    private UserManagementService userMgmtService;
    
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO addUsert(UserDTO user) {
        return userMgmtService.addUser(user);
    }
    
    @DELETE
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer deleteUser(@PathParam("username") String username) {
       userMgmtService.deleteUserByName(username);
       return 1;
    }
    
    @PUT
    @Path("/{username}")
    public UserDTO editUser(@PathParam("username") String username, UserDTO user) {
        if (!user.getUsername().equals(username)) {
            throw new IdNotMatchException("Username error");
        }
        return userMgmtService.editUser(user);
    }
    
    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam("username") String username) {
        return userMgmtService.getUser(username);
    }
    
    @GET
    @Path("/")
    public Collection<UserDTO> getUsers() {
        return userMgmtService.getUsers();
    }


    @POST
    @Path("/login")
    @SessionScoped
    @Consumes("application/json")
    public boolean login(@Context HttpServletRequest request, 
            @PathParam("username") String username, 
            @PathParam("password") String password ) {
       
        for (UserDTO acceptedUser : userMgmtService.getUsers()) {
            if (acceptedUser.getUsername().equals(username)&& 
                    acceptedUser.getPassword().equals(password)) {
                HttpSession session;
                session = request.getSession(true);
                session.setMaxInactiveInterval(300);
                session.setAttribute(username, acceptedUser);
                return true;
            }
        }
        return false;
    }
    
    @POST
    @Path("/logout")
    public String logout(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "Success invalidate this session";
    }

}

