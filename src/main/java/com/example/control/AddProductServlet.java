package com.example.control;

import com.example.entity.Product;
import com.example.entity.Receipt;
import com.example.entity.User;
import com.example.service.ProductService;
import com.example.service.ReceiptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addprod")
public class AddProductServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AddProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productID = Integer.parseInt(req.getParameter("product_id"));
        Product product = ProductService.getInstance().findProductById(productID);
        User user = (User) req.getSession().getAttribute("user");
        boolean check = ReceiptService.getInstance().addProduct(user, product);
        if(!check){
            req.setAttribute("errormessage", "product don't added");
            req.getRequestDispatcher("/res/errorPage.jsp").forward(req, resp);
        }

        Receipt receipt = ReceiptService.getInstance().getReceipt(user.getId());
        req.getSession().setAttribute("receipt", receipt);
        resp.sendRedirect("/products");
    }
}
