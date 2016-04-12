/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.codingmentor.ee.dto.UserDTO;


public class UserDTOTest {
    
    private static ValidatorFactory vf;
    private static Validator validator;
    private static SimpleDateFormat sdf;

    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Test
    public void goodUserDTOExample() throws ParseException {
        Date regDate, birthDay;
        regDate = sdf.parse("21/12/2012");
        birthDay = sdf.parse("01/01/1992");
        UserDTO user = new UserDTO("admin", "adminABC123", "firstName",
                    "lastName", birthDay, regDate, true);
           
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }
    
    @Test
    public void wrongUsername() throws ParseException {
        Date regDate, birthDay;
        regDate = sdf.parse("21/12/2012");
        birthDay = sdf.parse("01/01/1992");
        UserDTO user = new UserDTO("ad", "adminABC123", "firstName",
                    "lastName", birthDay, regDate, true);
             
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("The username is too short. (min 3 character)",
                violations.iterator().next().getMessage());
    }
    
    
    @Test
    public void regAfterBirthday() throws ParseException {
        Date regDate, birthDay;
        regDate = sdf.parse("21/12/2012");
        birthDay = sdf.parse("01/01/2015");
        UserDTO user = new UserDTO("adminAsUsername", "adminABC123", "firstName",
                    "lastName", birthDay, regDate, true);
             
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Registration is after the birthday",
                violations.iterator().next().getMessage());
        Assert.assertEquals("{AfterBirthday.message}", violations.iterator().next().getMessageTemplate());
    }
    
    

}
