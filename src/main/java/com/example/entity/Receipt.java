package com.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private User user;
    private List<Product> products;

    public Receipt() {
        user = new User();
        products = new ArrayList<>();
    }



    public Receipt(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
