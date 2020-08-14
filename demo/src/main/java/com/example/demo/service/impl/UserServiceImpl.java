package com.example.demo.service.impl;

import com.example.demo.model.mongo.User;
import com.example.demo.service.UserService;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MongoTemplate mongoTemplate = null;


    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public DeleteResult deleteUser(long id) {
        Criteria criteria = Criteria
                .where("id")
                .is("id");
        Query queryId = Query.query(criteria);

        DeleteResult result = mongoTemplate.remove(queryId, User.class);
        return result;

    }

    @Override
    public List<User> findUser(String userName, String node, int skip, int limit) {
        Criteria criteria = Criteria
                .where("userName")
                .regex(userName)
                .and("note")
                .regex("note");
        Query query = Query.query(criteria).limit(limit).skip(skip);
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

    @Override
    public List<User> updateUser(long id, String userName, String note) {
        return null;
    }

    @Override
    public User getUser(long id) {
        return mongoTemplate.findById(id, User.class);
    }
}
