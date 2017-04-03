package org.blog.dao;

import org.blog.Starter;
import org.blog.config.Database;
import org.blog.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Anand_Rajneesh on 3/24/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import(value = {Database.class, Starter.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Database.Helper helper;

    @Test
    public void testSave(){
        User user = new User();
        user.setEmail("ad@asd.com");
        user.setFirstName("asd");
        user.setLastName("asda");
        assertNotNull(userRepository.save(user));

    }

}
