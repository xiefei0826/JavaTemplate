package com.example.demo.service;

import com.example.demo.model.mongo.User;
import com.mongodb.client.result.DeleteResult;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    DeleteResult deleteUser(long id);

    List<User> findUser(String userName, String node, int skip, int limit);

    List<User> updateUser(long id, String userName, String note);

    public User getUser(long id);
}
