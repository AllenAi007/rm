package com.ai.sin.rm.exception;

/**
 * General exception for SinCurrency Application
 */
public class RelationshipManagerException extends RuntimeException {
    public RelationshipManagerException() {
    }

    public RelationshipManagerException(String message) {
        super(message);
    }

    public RelationshipManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RelationshipManagerException(Throwable cause) {
        super(cause);
    }

    public RelationshipManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
