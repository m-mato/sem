package cz.muni.pa165.sem.controller.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception used by Rest services.
 * Represents HTTP status 422 Unprocessable entity response code
 * Used for input params validation failure
 *
 * @author Kamil Triscik
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Invalid resource")
public class InvalidResourceException extends RuntimeException {
    public InvalidResourceException() {
        super();
    }
}
