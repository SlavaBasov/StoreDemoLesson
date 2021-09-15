package com.example.service;

import com.example.dao.impl.ProductDaoImpl;
import com.example.entity.Product;
import java.util.List;

public class ProductService {

    private static volatile ProductService INSTANCE = null;

    public ProductService() {
    }

    public static ProductService getInstance() {
        if (INSTANCE == null) {
            synchronized (ProductService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProductService();
                }
            }
        }
        return INSTANCE;
    }

    public Product createProduct(Product product){
        return ProductDaoImpl.getInstance().add(product);
    }

    public  Product findProductById(int id){
        return ProductDaoImpl.getInstance().findById(id);
    }

    public List<Product> findAllProducts(){
        return ProductDaoImpl.getInstance().findAll();
    }
}
