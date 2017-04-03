package org.blog.rest.extensions;

import org.blog.exceptions.ResourceException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Anand_Rajneesh on 4/3/2017.
 */
@Component
@Provider
public class ResourceExceptionMapper implements ExceptionMapper<ResourceException> {

    @Override
    public Response toResponse(ResourceException e) {
        return Response.status(e.status()).build();
    }
}
