package org.blog.exceptions;

import javax.ws.rs.core.Response.Status;

/**
 * Created by Anand_Rajneesh on 4/3/2017.
 */
public abstract class ResourceException extends RuntimeException {

    protected Status statusCode = Status.INTERNAL_SERVER_ERROR;

    public ResourceException() {
    }

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceException(Throwable cause) {
        super(cause);
    }

    public ResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Status status(){
        return statusCode;
    }


}
