package codingmentor.producer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Produces;


public class ValidatorProducer {

    @Produces
    public Validator produceValidator() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }
}
