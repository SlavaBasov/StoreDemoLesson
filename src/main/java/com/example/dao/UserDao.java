package com.example.dao;

import com.example.entity.User;

import java.util.List;

public interface UserDao {
    User add(User user);
    User findUser(String login, String password);
    List<User> findAll();
}
