package org.blog.dao;

import org.blog.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.core.CrudMethods;

/**
 * Created by Anand_Rajneesh on 3/24/2017.
 */
public interface UserRepository extends CRUDRepository<User, String> {

    User findByEmail(String email);
}
