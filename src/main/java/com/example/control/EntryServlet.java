package com.example.control;

import com.example.dao.impl.ReceiptDaoImpl;
import com.example.entity.Roles;
import com.example.entity.User;
import com.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/entry")
public class EntryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/res/entry.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = UserService.getInstance().findUserByLoginAndPassword(login, password);
        if (user != null){
            req.getSession().setAttribute("user",user);
            ReceiptDaoImpl.getInstance().deleteAll();

            if (user.getRole().getRole().toUpperCase().equals(Roles.ADMIN.toString())){
                resp.sendRedirect("/admin");
            } else if (user.getRole().getRole().toUpperCase().equals(Roles.USER.toString())){
                resp.sendRedirect("/profile");
            }
        } else {
            req.getSession().setAttribute("error", "Profile is not found");
            resp.sendRedirect("/entry");
        }
    }
}
