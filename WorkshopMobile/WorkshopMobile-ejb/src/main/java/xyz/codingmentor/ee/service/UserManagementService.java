package xyz.codingmentor.ee.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import xyz.codingmentor.ee.dto.UserDTO;
import xyz.codingmentor.ee.interceptor.BeanValidation;

@Singleton
@LocalBean
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@BeanValidation
public class UserManagementService implements Serializable{

    private List<UserDTO> userList = new ArrayList<>();

    //Add admin/admin and demo/demo user to userlist
    @PostConstruct
    private void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date regDate, birthDay;
        try {
            regDate = sdf.parse("21/12/2012");
            birthDay = sdf.parse("01/01/1992");
            userList.add(new UserDTO("admin", "adminABC123", "firstName",
                    "lastName", birthDay, regDate, true));
            
            userList.add(new UserDTO("demo", "demoABC123", "firstName",
                    "lastName", birthDay, regDate, false));
        } catch (ParseException ex) {
            Logger.getLogger(UserManagementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Lock(LockType.WRITE)
    public UserDTO addUser(UserDTO user) {
        userList.add(user);
        return user;
    }



    @Lock(LockType.WRITE)
    public UserDTO removeUser(String username) {
        for (UserDTO user : userList) {
            if (user.getUsername().equals(username)) {
                userList.remove(user);
                return user;
            }
        }
        throw new IllegalArgumentException("no such username");
    }

    @Lock(LockType.READ)
    public UserDTO getUser(String userName) {
        for (UserDTO user : userList) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        throw new IllegalArgumentException("no such username");
    }

    @Lock(LockType.WRITE)
    public UserDTO editUser(UserDTO user) {
        removeUser(user.getUsername());
        addUser(user);
        return user;
    }

    @Lock(LockType.READ)
    public Collection<UserDTO> getUsers() {
        return userList;
    }


}
