package com.example.dao.impl;

import com.example.dao.ProductDao;
import com.example.dbconnection.DBConnection;
import com.example.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    public static final String FIND_ALL = "SELECT * FROM product";
    public static final String FIND_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
    public static final String CREATE_PRODUCT = "INSERT INTO product (name, description, price, imgPath) VALUES (?,?,?,?)";


    public ProductDaoImpl() {
    }

    private static volatile ProductDaoImpl INSTANCE = null;

    public static ProductDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (ProductDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProductDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Product add(Product product) {
        int id = 0;
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,product.getName());
            statement.setString(2,product.getDescription());
            statement.setInt(3,product.getPrice());
            statement.setString(4,product.getImgPath());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()){
                id = keys.getInt(1);
            }
            product.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                products.add(new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


}
