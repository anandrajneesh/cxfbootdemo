package org.blog.exceptions;

import javax.ws.rs.core.Response;

/**
 * Created by Anand_Rajneesh on 3/30/2017.
 */
public class ResourceAlreadyExistsException extends ResourceException {

    {
        statusCode = Response.Status.PRECONDITION_FAILED;
    }

    public ResourceAlreadyExistsException() {
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ResourceAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
