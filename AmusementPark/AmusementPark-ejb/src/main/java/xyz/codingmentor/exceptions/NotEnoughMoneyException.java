
package xyz.codingmentor.exceptions;


public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException(String msg) {
        super(msg);
    }

    public NotEnoughMoneyException() {
        super();
    }
}

