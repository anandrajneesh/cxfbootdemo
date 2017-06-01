package org.blog.dao.custom;

import org.blog.models.User;

/**
 * Created by Anand_Rajneesh on 5/26/2017.
 */
public interface UserDao {

    User customFind(User args);

    User customUpdate(User user);
}
