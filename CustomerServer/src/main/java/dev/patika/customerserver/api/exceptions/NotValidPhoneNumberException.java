package dev.patika.customerserver.api.exceptions;

public class NotValidPhoneNumberException extends RuntimeException{
    public NotValidPhoneNumberException(String message) {
        super(message);
    }
}
