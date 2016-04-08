package xyz.codingmentor.ee.exception;



public class IdNotMatchException extends RuntimeException {

    public IdNotMatchException(String msg) {
        super(msg);
    }

    public IdNotMatchException() {
        super();
    }

}
