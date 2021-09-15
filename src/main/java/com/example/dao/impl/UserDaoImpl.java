package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.dbconnection.DBConnection;
import com.example.entity.Role;
import com.example.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {


    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM user JOIN role r on r.id = user.role_id WHERE login=?";
    public static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user JOIN role r on r.id = user.role_id WHERE login=? AND password=?";
    public static final String FIND_ALL = "SELECT * FROM user JOIN role r on r.id = user.role_id";
    public static final String CREATE_USER = "INSERT INTO user (login, password, role_id) VALUES (?,?,?)";

    public UserDaoImpl() {
    }

    private static volatile UserDaoImpl INSTANCE = null;

    public static UserDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        int id = 0;
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPassword());
            statement.setInt(3,2);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()){
                id = keys.getInt(1);
            }
            user.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            statement.setString(1,login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                RoleDaoImpl roleDAO = new RoleDaoImpl();
                Role role = roleDAO.findById(resultSet.getInt(4));
                user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user = null;
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                RoleDaoImpl roleDAO = new RoleDaoImpl();
                Role role = roleDAO.findById(resultSet.getInt(4));
                user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                RoleDaoImpl roleDAO = new RoleDaoImpl();
                Role role = roleDAO.findById(resultSet.getInt(4));
                users.add(new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
