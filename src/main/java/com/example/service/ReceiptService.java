package com.example.service;

import com.example.dao.ProductDAO;
import com.example.dao.ReceiptDAO;
import com.example.entity.Product;
import com.example.entity.Receipt;
import com.example.entity.User;

import java.util.List;

public class ReceiptService {

    private static volatile ReceiptService INSTANCE = null;

    public ReceiptService() {
    }

    public static ReceiptService getInstance() {
        if (INSTANCE == null) {
            synchronized (ReceiptService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReceiptService();
                }
            }
        }
        return INSTANCE;
    }

    public boolean addProduct(User user, Product product){
        return ReceiptDAO.getInstance().add(user, product);
    }

    public Receipt getReceipt(int userId){
        return ReceiptDAO.getInstance().getReceipt(userId);
    }

    public boolean deleteAll(){
        return ReceiptDAO.getInstance().deleteAll();
    }

}
