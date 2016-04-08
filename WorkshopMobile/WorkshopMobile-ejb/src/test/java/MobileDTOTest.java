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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import xyz.codingmentor.ee.dto.UserDTO;

/**
 *
 * @author basstik
 */
public class MobileDTOTest {
    
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
    public void isGoodUserDTOExample() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date regDate, birthDay;
        regDate = sdf.parse("21/12/2012");
        birthDay = sdf.parse("01/01/1992");
        UserDTO user = new UserDTO("admin", "admin", "firstName",
                    "lastName", birthDay, regDate, true);
            
        UserDTO user2 =new UserDTO("demo", "demo", "firstName",
                    "lastName", birthDay, regDate, false);
  
           
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }
    
    @Test
    public void wrongUsername() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date regDate, birthDay;
        regDate = sdf.parse("21/12/2012");
        birthDay = sdf.parse("01/01/1992");
        UserDTO user = new UserDTO("admin", "admin", "firstName",
                    "lastName", birthDay, regDate, true);
            

           
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidEmail() {

        Customer customer = new Customer("Szenes", "László", "dummyEmail");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid email address", violations.iterator().next().getMessage());
        Assert.assertEquals("dummyEmail", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Email.message}", violations.iterator().next().getMessageTemplate());
    }
}
