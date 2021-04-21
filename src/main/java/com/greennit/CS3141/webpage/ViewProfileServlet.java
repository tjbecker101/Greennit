package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Post;
import com.greennit.CS3141.entities.Thread;
import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.PostManager;
import com.greennit.CS3141.managers.ThreadManager;
import com.greennit.CS3141.managers.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view_profile")
public class ViewProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewProfileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("u");
        User user;
        String destPage = "view_profile.jsp";
        UserManager userManager = new UserManager();
        PostManager postManager = new PostManager();
        ThreadManager threadManager = new ThreadManager();

        try {
            user = userManager.getUser(username);
            List<Thread> userThreads = threadManager.getThreadsByUser(username);
            List<Post> userPosts = postManager.getPostsByUser(username);

            request.setAttribute("viewedUser", user);
            request.setAttribute("userThreads", userThreads);
            request.setAttribute("userPosts", userPosts);
        } catch (Exception e) {
            String failedUser = "User does not exist.";
            request.setAttribute("failedUserMessage", failedUser);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        userManager.exit();
        threadManager.exit();
        postManager.exit();
    }
}
