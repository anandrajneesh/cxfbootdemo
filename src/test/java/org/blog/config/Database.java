package org.blog.config;

import com.github.fakemongo.Fongo;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.List;

/**
 * Created by Anand_Rajneesh on 3/27/2017.
 */
@Configuration
public class Database{

    @Bean
    public MongoClient mongo() {
        Fongo fongo = new Fongo("mockDB");
        return fongo.getMongo();
    }

    @Bean
    public SimpleMongoDbFactory mongoDbFactory(MongoClient mongo) throws Exception {
        return new SimpleMongoDbFactory(mongo, "mockDB");
    }

    @Bean
    public Helper helper(SimpleMongoDbFactory simpleMongoDbFactory) {
        return new Helper(simpleMongoDbFactory);
    }

    @FunctionalInterface
    private interface MongoOp<R> {
        R apply(DBObject document, DBCollection collection);
    }

    @FunctionalInterface
    private interface CollectionOp<R> {
        R collection(String collection);
    }

    public static class Helper {

        private MongoTemplate mongoTemplate;

        public Helper(SimpleMongoDbFactory simpleMongoDbFactory) {
            this.mongoTemplate = new MongoTemplate(simpleMongoDbFactory);
        }

        private DBCollection getCollection(String collection) {
            return mongoTemplate.getCollection(collection);
        }

        public <R> CollectionOp<R> execute(DBObject document, MongoOp<R> op) {
            return (collection) -> op.apply(document, getCollection(collection));
        }

        private static final MongoOp<List<DBObject>> FIND = (query, coll) -> coll.find(query).toArray();

        private static final MongoOp<DBObject> FIND_ONE = (query, coll) -> coll.findOne(query);

        private static final MongoOp SAVE = (doc, coll) -> coll.save(doc);

        private static final MongoOp DELETE = (doc, coll) -> coll.remove(doc);

        public CollectionOp<List<DBObject>> find(DBObject query) {
            return execute(query, FIND);
        }

        public CollectionOp<DBObject> findOne(DBObject query) {
            return execute(query, FIND_ONE);
        }

        public CollectionOp delete(DBObject doc) {
            return execute(doc, DELETE);
        }

        public CollectionOp save(DBObject doc) {
            return execute(doc, SAVE);
        }

    }
}
