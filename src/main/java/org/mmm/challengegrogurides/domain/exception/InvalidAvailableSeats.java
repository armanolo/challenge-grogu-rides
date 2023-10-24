package org.mmm.challengegrogurides.domain.exception;

public class InvalidAvailableSeats extends RuntimeException {
    public InvalidAvailableSeats(String message) {
        super(message);
    }
}
