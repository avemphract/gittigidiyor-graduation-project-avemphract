package dev.patika.creditscoreserver.api.exceptionhandler;

public class PersonCreditScoreNotFoundException extends RuntimeException {
    public PersonCreditScoreNotFoundException(String message) {
        super(message);
    }
}
