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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/res/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(login, password, new Role(Roles.USER.toString()));
        if(UserService.getInstance().findUser(login,password)==null) {
            User userWithId = UserService.getInstance().createUser(user);
            req.getSession().setAttribute("user", userWithId);
            resp.sendRedirect("/profile");
        } else {
            resp.sendRedirect("/registration");
        }
    }
}
