package com.example.dbconnection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final DataSource dataSource;

    static {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl("jdbc:mysql://localhost:3306/storedemolesson");
        poolProperties.setUsername("root");
        poolProperties.setPassword("721996qwE");
        poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource = new DataSource(poolProperties);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
