package com.example.dao.impl;

import com.example.dao.RoleDao;
import com.example.dbconnection.DBConnection;
import com.example.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {

    public static final String FIND_ROLE_BY_ID = "SELECT * FROM role WHERE id=?";

    public RoleDaoImpl() {
    }

    private static volatile RoleDaoImpl INSTANCE = null;

    public static RoleDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (RoleDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RoleDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Role findById(int id) {
        Role role = new Role();
        try (Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(FIND_ROLE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                role = new Role(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return role;
    }
}
