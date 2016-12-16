package cz.muni.pa165.sem.controller.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception used by Rest services.
 * Represents HTTP status 409 Conflict response code.
 *
 * @author Kamil Triscik
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Resource can not be deleted")
public class ResourceDeleteException extends RuntimeException {
    public ResourceDeleteException(Throwable cause) {
        super(cause);
    }
}
