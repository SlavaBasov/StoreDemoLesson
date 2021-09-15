package com.example.dao;

import com.example.entity.User;

import java.util.List;

public interface UserDao {
    User add(User user);
    User findUserByLogin(String login);
    User findUserByLoginAndPassword(String login, String password);
    List<User> findAll();
}
