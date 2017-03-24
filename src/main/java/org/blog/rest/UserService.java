package org.blog.rest;

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
public class UserService {

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") long id){
        User user =  new User();
        user.set_id(Long.toString(id));
        user.setLastName("someh");
        user.setFirstName("asda");
        user.setEmail("asdada");
        return user;
    }
}
