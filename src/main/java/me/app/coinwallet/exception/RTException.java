package me.app.coinwallet.exception;

public class RTException extends RuntimeException{
    public RTException(String message, Throwable e) {
        super(message, e);
    }

    public RTException(String message) {
        super(message);
    }

    public RTException(Throwable e) {
        super(e);
    }
}
