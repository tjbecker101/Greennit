package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.User;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDao = new UserDAO();

        User user = userDao.checkLogin(username, password);
        String destPage = "login.jsp";

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            destPage = "index.jsp";
        } else {
            String message = "Invalid username/password";
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
