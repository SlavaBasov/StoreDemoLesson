package com.example.control;

import com.example.entity.Role;
import com.example.entity.Roles;
import com.example.entity.User;
import com.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/out")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("user", null);
        req.getSession().setAttribute("receipt", null);
        getServletContext().getRequestDispatcher("/").forward(req,resp);
    }
}
