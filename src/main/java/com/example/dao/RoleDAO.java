package com.example.dao;

import com.example.dbconnection.DBConnection;
import com.example.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDAO implements BaseDAO<Role> {

    public static final String FIND_ROLE_BY_ID = "SELECT * FROM role WHERE id=?";

    public RoleDAO() {
    }

    private static volatile RoleDAO INSTANCE = null;

    public static RoleDAO getInstance() {
        if (INSTANCE == null) {
            synchronized (RoleDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RoleDAO();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Role add(Role role) {
        return null;
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
