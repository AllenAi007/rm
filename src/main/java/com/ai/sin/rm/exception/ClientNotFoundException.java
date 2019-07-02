package com.ai.sin.rm.exception;

/**
 * Client not found
 */
public class ClientNotFoundException extends RelationshipManagerException {

    public ClientNotFoundException() {
    }

    public ClientNotFoundException(Integer clientId) {
        super("Client id " + clientId + " does not exist.");
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundException(Throwable cause) {
        super(cause);
    }

    public ClientNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
