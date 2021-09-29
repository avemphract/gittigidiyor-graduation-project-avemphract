package dev.patika.creditscoreserver.api.exceptionhandler;

public class PersonCreditScoreAlreadyExistsException extends RuntimeException{
    public PersonCreditScoreAlreadyExistsException(String message) {
        super(message);
    }
}
