package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    List<User> findByUserNameLike(String userName);
    @Query("{'id':?0,'userName':?1}")
    User find(long id,String userName);
    User findUserByIdOrUserName(long id,String userName);
}
