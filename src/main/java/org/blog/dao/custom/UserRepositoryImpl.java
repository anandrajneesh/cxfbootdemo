package org.blog.dao.custom;

import org.blog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Anand_Rajneesh on 5/26/2017.
 */
@Component
public class UserRepositoryImpl  implements UserDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public User customFind(User args) {
        Query query = new QueryBuilder(args).query();
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User customUpdate(User user) {
        mongoTemplate.findAndModify(idQuery.apply(user.getId()), new UpdateBuilder(user).update(), User.class);
        return user;
    }


    private static final Function<String, Query> idQuery = arg -> {
        Criteria criteria = new Criteria();
        criteria.and("id").is(arg);
        return new Query(criteria);
    };

    private static class UpdateBuilder {
        private Update update;
        private User user;

        private UpdateBuilder(User user){
            this.user = user;
            this.update = new Update();
            set("email",user::getEmail);
            set("firstName", user::getFirstName);
            set("lastName", user::getLastName);
        }

        private Update update(){
            return this.update;
        }

        private void set(String name, Supplier<Object> supp){
            Optional.ofNullable(supp.get()).ifPresent(arg -> this.update.set(name, arg));
        }
    }

    private static class QueryBuilder{
        private Criteria criteria;
        private User user;

        private QueryBuilder(User user) {
            this.user = user;
            this.criteria = new Criteria();
            addToCriteria("email", user::getEmail);
            addToCriteria("firstName", user::getFirstName);
            addToCriteria("lastName", user::getLastName);
            addToCriteria("id",user::getId);
        }

        private Query query(){
            return Query.query(this.criteria);
        }

        private void addToCriteria(String name, Supplier<Object> supp){
            Optional.ofNullable(supp.get()).ifPresent(arg -> this.criteria.and(name).is(arg));
        }
    }
}
