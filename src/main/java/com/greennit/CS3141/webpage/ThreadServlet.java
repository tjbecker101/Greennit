package com.greennit.CS3141.webpage;


import com.greennit.CS3141.entities.Post;
import com.greennit.CS3141.managers.PostManager;
import com.greennit.CS3141.managers.ThreadManager;
import com.greennit.CS3141.entities.Thread;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/thread")
public class ThreadServlet extends HttpServlet {

    public ThreadServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ThreadManager threadManager = new ThreadManager();
        PostManager postManager = new PostManager();
        Thread thread;
        String destPage;
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
        }

        if (id == -1) {
            destPage = "index.jsp";
        } else {
            thread = threadManager.getThread(id);
            List<Post> posts = postManager.getPostsByThread(thread.getId());
            request.setAttribute("currentThread", thread);
            request.setAttribute("posts", posts);
            destPage = "thread.jsp?id=" + thread.getId();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        threadManager.exit();
    }
}
