
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.codingmentor.ee.dto.MobileDTO;

/**
 *
 * @author basstik
 */
public class MobileDTOTest {
    
    private static ValidatorFactory vf;
    private static Validator validator;

    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();

    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Test
    public void goodMobileDTOExample() {
        MobileDTO mobile = new MobileDTO("Abc", "deF", 1, 0);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }
    
    @Test
    public void wrongTypeOfMobileDTO(){
        MobileDTO mobile = new MobileDTO("ab", "Samsun", 40000, 10);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("The type of mobile require min 3 character",
                violations.iterator().next().getMessage());
    }


}
