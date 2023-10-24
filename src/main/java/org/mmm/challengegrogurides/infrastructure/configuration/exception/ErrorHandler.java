package org.mmm.challengegrogurides.infrastructure.configuration.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.mmm.challengegrogurides.domain.exception.DuplicatedDniException;
import org.mmm.challengegrogurides.domain.exception.DuplicatedIdException;
import org.mmm.challengegrogurides.domain.exception.EmptyListException;
import org.mmm.challengegrogurides.domain.exception.InvalidAvailableSeats;
import org.mmm.challengegrogurides.domain.exception.InvalidDniException;
import org.mmm.challengegrogurides.domain.exception.InvalidEndTimeException;
import org.mmm.challengegrogurides.domain.exception.InvalidRentVehicleIdException;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.domain.exception.InvalidUserNameException;
import org.mmm.challengegrogurides.domain.exception.InvalidVehicleIdException;
import org.mmm.challengegrogurides.domain.exception.NotFoundRentException;
import org.mmm.challengegrogurides.domain.exception.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value
            = { UnexpectedTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseBody unexpectedType(RuntimeException ex) {

        return new ErrorResponseBody(ex.getMessage());

    }

    @ExceptionHandler(value
            = {
            DuplicatedIdException.class, InvalidVehicleIdException.class, InvalidAvailableSeats.class,
            EmptyListException.class, InvalidUserIdException.class, InvalidDniException.class,
            InvalidUserNameException.class, DuplicatedDniException.class, InvalidRentVehicleIdException.class,
            InvalidEndTimeException.class
        }
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseBody handleConflict(RuntimeException ex) {

        return new ErrorResponseBody(ex.getMessage());

    }

    @ExceptionHandler(value= { NotFoundUserException.class, NotFoundRentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponseBody handleNotFoundConflict(RuntimeException ex) {

        return new ErrorResponseBody(ex.getMessage());

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponseBody handleConstraintViolationException(ConstraintViolationException e) {

        return new ErrorResponseBody("values must not be empty");
    }
}
