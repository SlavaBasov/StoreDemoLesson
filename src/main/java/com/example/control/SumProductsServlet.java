package com.example.control;

import com.example.entity.Receipt;
import com.example.entity.User;
import com.example.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.IntStream;

@WebServlet("/sum")
public class SumProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Receipt receipt = ReceiptService.getInstance().getReceipt(user.getId());
        //Receipt receipt1 = (Receipt) req.getSession().getAttribute("receipt");
        int sum = receipt.getProducts().stream().mapToInt(s-> s.getPrice()).sum();
        req.getSession().setAttribute("sum", sum);
        getServletContext().getRequestDispatcher("/basket").forward(req, resp);
    }



}
