package cz.muni.pa165.sem.controller.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception used by Rest services.
 * Represents HTTP status 422 Unprocessable entity response code
 *
 * @author Kamil Triscik
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Requested resource already exists")
public class ExistingResourceException extends RuntimeException {
    public ExistingResourceException(Throwable cause) {
        super(cause);
    }
}
