package codingmentor.config.service;

import codingmentor.dto.UserDTO;
import codingmentor.exception.IdNotMatchException;
import codingmentor.interceptor.BeanValidation;
import codingmentor.service.UserManagementService;
import java.util.Collection;
import java.util.List;
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


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
//@BeanValidation
public class UserRESTService {

    @Inject
    private UserManagementService userMgmtService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO addUsert(UserDTO user) {
        return userMgmtService.addUser(user);
    }
    
    @DELETE
    @Path("/{username}")
    public int deleteUser(@PathParam("username") String username) {
        return userMgmtService.deleteUserByName(username);
    }
    
    @PUT
    @Path("/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO editUser(@PathParam("userName") String username, UserDTO user) {
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
        HttpSession session = null;
        for (UserDTO acceptedUser : userMgmtService.getUsers()) {
            if (acceptedUser.getUsername().equals(username)) {
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

