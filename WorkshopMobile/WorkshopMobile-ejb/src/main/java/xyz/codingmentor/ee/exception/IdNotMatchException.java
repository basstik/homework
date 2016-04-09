package xyz.codingmentor.ee.exception;

import javax.ejb.ApplicationException;


@ApplicationException(rollback = true)
public class IdNotMatchException extends RuntimeException {

    public IdNotMatchException(String msg) {
        super(msg);
    }

    public IdNotMatchException() {
        super();
    }

}
