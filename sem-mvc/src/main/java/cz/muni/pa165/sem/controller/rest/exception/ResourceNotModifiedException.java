package cz.muni.pa165.sem.controller.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception used by Rest services.
 * Represents HTTP status 304 Not modified response code.
 *
 * @author Kamil Triscik
 */
@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "Requested resource not modified")
public class ResourceNotModifiedException extends RuntimeException {
    public ResourceNotModifiedException(Throwable cause) {
        super(cause);
    }
}
