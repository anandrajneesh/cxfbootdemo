package org.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Service;
import org.blog.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Anand_Rajneesh on 3/23/2017.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
@Api(value = "user")
public class UserService {

    @GET
    @Path("/{id}")
    @ApiOperation(value="Get user by id", response = User.class)
    public User getUserById(@ApiParam(value="id of user", required=true) @PathParam("id") long id){
        User user =  new User();
        user.setId(Long.toString(id));
        user.setLastName("someh");
        user.setFirstName("asda");
        user.setEmail("asdada");
        return user;
    }
}
