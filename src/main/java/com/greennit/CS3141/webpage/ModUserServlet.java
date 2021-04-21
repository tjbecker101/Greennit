package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Post;
import com.greennit.CS3141.entities.Thread;
import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.PostManager;
import com.greennit.CS3141.managers.ThreadManager;
import com.greennit.CS3141.managers.UserManager;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mod_user")
public class ModUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("u");
        User moderator = (User) request.getSession().getAttribute("user");
        User user;
        String destPage = "view_profile?u=" + username;
        UserManager userManager = new UserManager();

        try {
            user = userManager.getUser(username);
            if (moderator.getPermission() >= 2) {
                userManager.updatePermission(username, 2);
            }
            request.setAttribute("viewedUser", user);
        } catch (Exception e) {
            String failedUser = "User does not exist.";
            request.setAttribute("failedUserMessage", failedUser);
        }

        response.sendRedirect(destPage);
        userManager.exit();

    }
}
