package com.example.dao.impl;

import com.example.dao.ReceiptDao;
import com.example.dbconnection.DBConnection;
import com.example.entity.Product;
import com.example.entity.Receipt;
import com.example.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDaoImpl implements ReceiptDao {

    private static final Logger logger = LogManager.getLogger(ReceiptDaoImpl.class);

    public static final String ADD_PRODUCT_IN_RECEIPT = "INSERT INTO receipt (user_id, product_id) VALUES (?,?)";
    public static final String FIND_USER_BY_USERID = "SELECT * FROM receipt JOIN user u on u.id = receipt.user_id WHERE user_id=?";
    public static final String FIND_PRODUCTS_BY_USERID = "SELECT * FROM receipt JOIN product p on p.id = receipt.product_id WHERE user_id=?";
    public static final String DELETE_ALL = "TRUNCATE TABLE receipt";


    public ReceiptDaoImpl() {
    }

    private static volatile ReceiptDaoImpl INSTANCE = null;

    public static ReceiptDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (ReceiptDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReceiptDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public boolean add(User user, Product product) {
        boolean check = false;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_IN_RECEIPT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getId());
            statement.setInt(2, product.getId());
            int key = 0;
            key = statement.executeUpdate();
            if(key != 0){
                check = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public Receipt getReceipt(int userId) {
        logger.log(Level.INFO, "вход в метод getReceipt c id = " + userId);
        User user = findUser(userId);
        logger.log(Level.INFO, "найден юзер " + user.getLogin());
        List<Product> products = findAllProducts(userId);
        logger.log(Level.INFO, "сформирован список продуктов " + products.toString());
        return new Receipt(user, products);
    }

    @Override
    public User findUser(int userId) {
        User user = null;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
                        RoleDaoImpl.getInstance().findById(resultSet.getInt(7)));
                logger.log(Level.INFO, "создан юзер " + user.getLogin() + " в методе findUser");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Product> findAllProducts(int userId){
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_PRODUCTS_BY_USERID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getInt(7), resultSet.getString(8));
                products.add(product);
                logger.log(Level.INFO, "добавлен продукт " + product.getName() + " в лист продуктов");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "вход в блок catch, ошибка");
            e.printStackTrace();

        }
        return products;
    }

    @Override
    public boolean deleteAll() {
        boolean check = false;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL);
            statement.executeUpdate();
            check = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

}
