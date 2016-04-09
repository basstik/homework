package xyz.codingmentor.ee.exception;

import javax.ejb.ApplicationException;


@ApplicationException(rollback = true)
public class ValidationException extends RuntimeException{
    
    public ValidationException(String msg) {
        super(msg);
    }

    public ValidationException() {
        super();
    }

}
