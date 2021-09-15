package com.example.dao;

import com.example.entity.Product;
import com.example.entity.Receipt;
import com.example.entity.User;

import java.util.List;

public interface ReceiptDao {
    boolean add(User user, Product product);
    Receipt getReceipt(int userId);
    User findUser(int userId);
    List<Product> findAllProducts(int userId);
    boolean deleteAll();




}
