package org.blog.exceptions;

import javax.ws.rs.core.Response;

/**
 * Created by Anand_Rajneesh on 3/30/2017.
 */
public class ResourceNotFoundException extends ResourceException {

    {
        statusCode = Response.Status.NOT_FOUND;
    }

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
