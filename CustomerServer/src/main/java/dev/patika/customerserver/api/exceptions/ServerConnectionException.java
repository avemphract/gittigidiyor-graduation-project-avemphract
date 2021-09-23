package dev.patika.customerserver.api.exceptions;

public class ServerConnectionException extends RuntimeException {
    public ServerConnectionException() {
        super("The service trying to connect is not up and running.");
    }
}
