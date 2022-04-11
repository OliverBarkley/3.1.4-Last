package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    User add (User user);
    List<User> getAllUsers();
    User getUserById(long id);
    User userEdit(User user,long id);
    void delete(long id);
    User getUserByUsername(String username);
}
