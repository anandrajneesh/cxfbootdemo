package org.blog.dao;

import org.blog.dao.custom.UserDao;
import org.blog.models.User;

/**
 * Created by Anand_Rajneesh on 3/24/2017.
 */
public interface UserRepository extends CRUDRepository<User, String>, UserDao {

    User findByEmail(String email);
}
