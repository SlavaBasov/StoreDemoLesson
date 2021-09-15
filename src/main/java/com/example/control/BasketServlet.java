package com.example.control;

import com.example.entity.Product;
import com.example.entity.Receipt;
import com.example.entity.User;
import com.example.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sum(req, resp);
        getServletContext().getRequestDispatcher("/res/basket.jsp").forward(req, resp);
    }

    private int sum(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        //Receipt receipt = ReceiptService.getInstance().getReceipt(user.getId());
        Receipt receipt = (Receipt) req.getSession().getAttribute("receipt");
        int sum = 0;
        if(receipt != null) {
            for (Product p : receipt.getProducts()) {
                sum += p.getPrice();
            }
        }
        //sum = receipt.getProducts().stream().mapToInt(s-> s.getPrice()).sum();
        req.getSession().setAttribute("sum", sum);
        return sum;
    }
}
