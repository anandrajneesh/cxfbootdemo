package org.blog.dao;

import com.github.fakemongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import org.blog.Starter;
import org.blog.config.Database;
import org.blog.models.User;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.*;

/**
 * Created by Anand_Rajneesh on 3/24/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import(value = {Database.class, Starter.class})
@ContextConfiguration
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
