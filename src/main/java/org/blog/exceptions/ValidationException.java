package org.blog.exceptions;

import javax.ws.rs.core.Response;

/**
 * Created by Anand_Rajneesh on 3/30/2017.
 */
public class ValidationException extends ResourceException {

    {
        statusCode = Response.Status.BAD_REQUEST;
    }

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
