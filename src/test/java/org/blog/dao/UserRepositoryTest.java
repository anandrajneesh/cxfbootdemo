package org.blog.dao;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.blog.Starter;
import org.blog.config.Database;
import org.blog.models.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Anand_Rajneesh on 3/24/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import(value = {Database.class, Starter.class})
public class UserRepositoryTest {

    @Autowired
    private Database.Helper helper;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepository;

   @Rule
   public MongoDbRule embeddedMongoDbRule = newMongoDbRule().defaultSpringMongoDb("mockDB");

    @Test
    @ShouldMatchDataSet(location = "/user/basicusersave.json")
    public void basicUserSave(){
        User user = new User();
        user.setEmail("anandrajneesh@outlook.com");
        user.setFirstName("Anand");
        user.setLastName("Rajneesh");
        assertNotNull(userRepository.save(user));
    }

    /*@UsingDataSet()
    public void testFind(){
    }*/

}
