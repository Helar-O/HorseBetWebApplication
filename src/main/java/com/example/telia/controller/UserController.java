package com.example.telia.controller;

import com.example.telia.dao.UserDao;
import com.example.telia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    // TODO: remove mapping after production
    @GetMapping("/users")
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @PostMapping("/user/register")
    public User addUser(@RequestBody User user) throws Exception {
        return userDao.addNewUser(user);
    }

    @PostMapping("/user/login")
    public Optional<User> getUser(@RequestBody User user) throws Exception {
        return userDao.getUser(user);
    }
}
