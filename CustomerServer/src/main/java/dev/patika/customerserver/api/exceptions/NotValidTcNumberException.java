package dev.patika.customerserver.api.exceptions;

public class NotValidTcNumberException extends RuntimeException{
    public NotValidTcNumberException(String message) {
        super(message);
    }
}
