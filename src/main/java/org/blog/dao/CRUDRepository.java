package org.blog.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by Anand_Rajneesh on 3/30/2017.
 */
@NoRepositoryBean
public interface CRUDRepository<Entity, ID extends Serializable> extends MongoRepository<Entity, ID> {
}
