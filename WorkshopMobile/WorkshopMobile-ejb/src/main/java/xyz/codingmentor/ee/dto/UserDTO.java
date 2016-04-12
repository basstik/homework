package xyz.codingmentor.ee.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.*;
import xyz.codingmentor.ee.annotation.Validate;
import xyz.codingmentor.ee.dto.constraints.RegistrationAfterBirthdayConstraint;

@Validate
@RegistrationAfterBirthdayConstraint
public class UserDTO implements Serializable{

    //this is the id of User
    @NotNull
    @Pattern(regexp = "....*", message = "The username is too short. (min 3 character)")
    private String username;
    
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9=+<>.,])[A-Za-z0-9=+<>.,]{6,}$")
    private String password;
    
    private String firstName;
    private String lastname;
    
    @Past
    private Date dateOfBirth;   //nem kötelező megadni
    @NotNull
    private Date registrationDate;
    
    private boolean admin;
    
    private List<MobileDTO> mobileList;

    public UserDTO() {
        //empty default contructor for REST API
    }

    
    public UserDTO(String username, String password, String firstName, String lastname, Date dateOfBirth, Date registrationDate, boolean admin) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.admin = admin;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
  
    public List<MobileDTO> getCart() {
        return mobileList;
    }

    public Integer addMobile(MobileDTO mobile) {
        mobileList.add(mobile);
        return mobileList.size();
    }
          
}
