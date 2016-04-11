package xyz.codingmentor.ee.exception;

import javax.ejb.ApplicationException;


@ApplicationException
public class ValidationException extends RuntimeException{
    
    public ValidationException(String msg) {
        super(msg);
    }

    public ValidationException() {
        super();
    }

}
