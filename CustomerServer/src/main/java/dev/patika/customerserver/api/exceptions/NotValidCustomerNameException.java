package dev.patika.customerserver.api.exceptions;

public class NotValidCustomerNameException extends RuntimeException{
    public NotValidCustomerNameException(String message) {
        super(message);
    }
}
