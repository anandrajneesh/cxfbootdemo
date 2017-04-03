package org.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Anand_Rajneesh on 3/24/2017.
 */
@Path("/ping")
@Service
@Api(value = "ping")
@Produces(MediaType.TEXT_PLAIN)
public class Ping {

    @GET
    @ApiOperation(value="Health Check")
    @ApiResponse(code=200, message ="")
    public Response health(){
        return Response.ok().build();
    }
}
