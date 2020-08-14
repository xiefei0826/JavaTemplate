package com.example.demo.api;

import com.example.demo.model.mongo.User;
import com.example.demo.repository.mongo.UserRepository;
import com.example.demo.service.UserService;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

@RestController
@RequestMapping("/mongo")
public class mongoController {
    @Autowired
    private UserService userService = null;
    @Autowired
    private UserRepository userRepository=null;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/get")
    public User GetUser(long id) {
        return userService.getUser(id);
    }

    @GetMapping("/find")
    public List<User> findUser(String userName, String note, int skip, int limit) {

        return userService.findUser(userName, note, skip, limit);
    }

    @PostMapping("/update")
    public List<User> updateUser(long id, String userName, String note) {

        return userService.updateUser(id, userName, note);
    }

    @DeleteMapping("/delete")
    public DeleteResult deleteUser(long id) {

        return userService.deleteUser(id);
    }
    @GetMapping("/byName")
    public List<User> findByUserName(String userName) {

        return userRepository.findByUserNameLike(userName);
    }
}
