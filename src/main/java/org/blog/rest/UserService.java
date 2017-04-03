package org.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.blog.dao.UserRepository;
import org.blog.service.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.blog.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Anand_Rajneesh on 3/23/2017.
 */
@Api(value = "user")
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class UserService {

    @Autowired
    private UserFacade userFacade;

    @ApiOperation(value="Get user by id", response = User.class)
    @GET
    @Path("/{id}")
    public Response getUserById(@ApiParam(value="id of user", required=true) @PathParam("id") String id){
        User existingUser = userFacade.get(id);
        return Response.ok(existingUser).build();
    }

    @ApiOperation(value ="create a new user", response = User.class)
    @POST
    public Response saveNewUser(@ApiParam(required = true) User user){
        user = userFacade.create(user);
        return Response.ok(user).build();
    }

    @ApiOperation(value=" delete user")
    @DELETE
    @Path("/{id}")
    public Response delete(@ApiParam(value="id of user", required=true) @PathParam("id")String id){
        userFacade.delete(id);
        return Response.noContent().build();
    }

}
