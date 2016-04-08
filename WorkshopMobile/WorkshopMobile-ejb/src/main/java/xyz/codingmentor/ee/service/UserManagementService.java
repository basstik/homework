package xyz.codingmentor.ee.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import xyz.codingmentor.ee.dto.UserDTO;

@Singleton
@LocalBean
@Startup
public class UserManagementService {

    private List<UserDTO> userList = new ArrayList<>();

    //Add admin/admin and demo/demo user to userlist
    @PostConstruct
    private void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date regDate, birthDay;
        try {
            regDate = sdf.parse("21/12/2012");
            birthDay = sdf.parse("01/01/1992");
            userList.add(new UserDTO("admin", "admin", "firstName",
                    "lastName", birthDay, regDate, true));
            
            userList.add(new UserDTO("demo", "demo", "firstName",
                    "lastName", birthDay, regDate, false));
        } catch (ParseException ex) {
            Logger.getLogger(UserManagementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserDTO addUser(UserDTO user) {
        userList.add(user);
        return user;
    }


    public int removeUser(UserDTO user) {
        boolean wasPresent = userList.remove(user);
        if (wasPresent == true) {
            return 1;
        }
        throw new IllegalArgumentException("no such username");
    }

    public int deleteUserByName(String username) {
        for (UserDTO user : userList) {
            if (user.getUsername().equals(username)) {
                return removeUser(user);
            }
        }
        throw new IllegalArgumentException("no such username");
    }

    public UserDTO getUser(String userName) {
        for (UserDTO user : userList) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        throw new IllegalArgumentException("no such username");
    }


    public UserDTO editUser(UserDTO user) {
        deleteUserByName(user.getUsername());
        addUser(user);
        return user;
    }

    public Collection<UserDTO> getUsers() {
        return userList;
    }


}
