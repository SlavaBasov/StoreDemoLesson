package com.example.service;

import com.example.dao.UserDAO;
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
        return UserDAO.getInstance().add(user);
    }

    public User findUser(String login, String password){
        return UserDAO.getInstance().findUser(login,password);
    }
}
