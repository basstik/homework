package xyz.codingmentor.ee.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ApplicationException;


@ApplicationException
public class ValidationException extends RuntimeException{

    private static final Logger LOG = Logger.getLogger(ValidationException.class.getName());
    
    
    public ValidationException(String msg) {
        super(msg);
        LOG.log(Level.SEVERE, msg);
    }

    public ValidationException() {
        super();
    }

}
