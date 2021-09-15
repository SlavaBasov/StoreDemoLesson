package com.example.service;

import com.example.dao.impl.UserDaoImpl;
import com.example.entity.User;

public class UserService {

    private static volatile UserService INSTANCE = null;

    public UserService() {
    }

    public static UserService getInstance() {
        if (INSTANCE == null) {
            synchronized (UserService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public User createUser(User user){
        return UserDaoImpl.getInstance().add(user);
    }

    public User findUserByLogin(String login){
        return UserDaoImpl.getInstance().findUserByLogin(login);
    }
    public User findUserByLoginAndPassword(String login, String password){
        return UserDaoImpl.getInstance().findUserByLoginAndPassword(login,password);
    }
}
