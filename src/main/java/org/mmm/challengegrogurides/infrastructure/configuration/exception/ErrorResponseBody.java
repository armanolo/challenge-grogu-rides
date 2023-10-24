package org.mmm.challengegrogurides.infrastructure.configuration.exception;

public class ErrorResponseBody {

    private String error;

    public String getError() {
        return error;
    }

    public ErrorResponseBody(String errorMessage) {
        this.error = errorMessage;
    }

}
