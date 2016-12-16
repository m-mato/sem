package cz.muni.pa165.sem.controller.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception used by Rest services.
 * Represents HTTP status 404 Not found response code
 *
 * @author Kamil Triscik
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested resource was not found")
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
