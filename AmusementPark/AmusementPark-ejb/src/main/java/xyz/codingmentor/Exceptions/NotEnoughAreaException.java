
package xyz.codingmentor.Exceptions;


public class NotEnoughAreaException extends RuntimeException {

    public NotEnoughAreaException(String msg) {
        super(msg);
    }

    public NotEnoughAreaException() {
        super();
    }
}
