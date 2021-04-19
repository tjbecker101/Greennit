package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Subgreennit;
import com.greennit.CS3141.managers.PostManager;
import com.greennit.CS3141.managers.SubgreennitManager;
import com.greennit.CS3141.managers.ThreadManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/create_post")
public class PostCreationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public PostCreationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PostManager postManager = new PostManager();
        SubgreennitManager subgreennitManager = new SubgreennitManager();

        String content = request.getParameter("content");
        String author = request.getParameter("author");
        int hostThread = Integer.parseInt(request.getParameter("hostThread"));


        String destPage = "create_post.jsp";

        if (content.equals("")) {
            String message = "Please Include the Content";
            request.setAttribute("message", message);
        } else if (author.equals("")) {
            String message = "Please Sign in";
            request.setAttribute("message", message);
        } else {
            postManager.createPost(hostThread, author, content);
            destPage = "thread?id="+hostThread;
            response.sendRedirect(destPage);
            postManager.exit();
            subgreennitManager.exit();
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        postManager.exit();
        subgreennitManager.exit();
    }
}
