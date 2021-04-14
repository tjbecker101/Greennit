package com.greennit.CS3141.webpage;

import com.greennit.CS3141.managers.ThreadManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/create_thread")
public class ThreadCreationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ThreadCreationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");

        //int host = Integer.parseInt(request.getParameter("host"));
        //System.out.println(host);
        int host = 1;

        ThreadManager threadManager = new ThreadManager();
        String destPage = "create_thread.jsp";
        if(title.equals("")) {
            String message = "Please Include a title";
            request.setAttribute("message", message);
            System.out.println("test1");
        }
        else if(content.equals("")){
            String message = "Please Include the Content of the Thread";
            request.setAttribute("message", message);
        }
        else if(author.equals("")){
            String message = "Please Sign in";
            request.setAttribute("message", message);
        }else{
            threadManager.createThread(host, title, author, content, new Timestamp(System.currentTimeMillis()));
            destPage = "Thread_Layout.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        threadManager.exit();
    }
}

