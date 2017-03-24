package org.blog.rest;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Anand_Rajneesh on 3/24/2017.
 */
@Path("/ping")
@Service
public class Ping {

    @GET
    public Response health(){
        return Response.ok().build();
    }
}
