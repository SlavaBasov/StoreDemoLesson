package com.example.dao;

import com.example.entity.Product;

import java.util.List;

public interface ProductDao {
    Product add(Product t);
    Product findById(int id);
    List<Product> findAll();
}
