package codingmentor.exception;

import codingmentor.interceptor.Loggable;


public class IdNotMatchException extends RuntimeException {

    public IdNotMatchException(String msg) {
        super(msg);
    }

    public IdNotMatchException() {
        super();
    }

}
