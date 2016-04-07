package codingmentor.service;

import codingmentor.dto.MobileDTO;
import codingmentor.dto.UserDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@Singleton
@LocalBean
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UserManagementService {

    private List<MobileDTO> mobilList = new ArrayList<>();
    private List<UserDTO> userList = new ArrayList<>();

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

    @Lock(LockType.WRITE)
    public Integer addUser(UserDTO user) {
        userList.add(user);
        return userList.size();
    }

    @Lock(LockType.WRITE)
    public int addMobile(MobileDTO mobile) {
        mobilList.add(mobile);
        return mobilList.size();
    }

    public synchronized int removeUser(UserDTO user) {
        boolean wasPresent = userList.remove(user);
        if (wasPresent == true) {
            return userList.size();
        }
        throw new IllegalArgumentException("no such username");
    }

    public synchronized int removeUserByName(String username) {
        for (UserDTO user : userList) {
            if (user.getUsername().equals(username)) {
                return removeUser(user);
            }
        }
        throw new IllegalArgumentException("no such username");
    }

    public synchronized int removeMobile(MobileDTO product) {
        mobilList.remove(product);
        return mobilList.size();
    }

    public UserDTO getUser(String userName) {
        for (UserDTO user : userList) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        throw new IllegalArgumentException("no such user");
    }

    public MobileDTO getMobile(String id) {
        for (MobileDTO mobile : mobilList) {
            if (mobile.getId().equals(id)) {
                return mobile;
            }
        }
        throw new IllegalArgumentException("no such mobile");
    }

    public UserDTO editUser(UserDTO user) {
        removeUserByName(user.getUsername());
        addUser(user);
        return user;
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public List<MobileDTO> getProductList() {
        return mobilList;
    }

}
