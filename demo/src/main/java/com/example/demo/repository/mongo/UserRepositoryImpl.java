package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User findUserByIdOrUserName(long id,String userName){
        Criteria criteriaId=Criteria.where("id").is(id);
        Criteria criteriaUserName=Criteria.where("userName").is(userName);

        Criteria criteria1=new Criteria();

        criteria1.orOperator(criteriaId,criteriaUserName);

        Query query =Query.query(criteria1);
        return mongoTemplate.findOne(query,User.class);
    }
}
