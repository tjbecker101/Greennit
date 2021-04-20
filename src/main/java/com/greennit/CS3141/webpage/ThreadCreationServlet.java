package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Subgreennit;
import com.greennit.CS3141.managers.ThreadManager;
import com.greennit.CS3141.managers.SubgreennitManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/create_thread")
public class ThreadCreationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ThreadCreationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ThreadManager threadManager = new ThreadManager();
        SubgreennitManager subgreennitManager = new SubgreennitManager();

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");

        String strHost = request.getParameter("host");
        List<Subgreennit> SGList = subgreennitManager.getSubgreennits(strHost);


        String destPage = "create_thread.jsp";
        if(title.equals("")) {
            String message = "Please Include a title";
            request.setAttribute("message", message);
        }
        else if(content.equals("")){
            String message = "Please Include the Content of the Thread";
            request.setAttribute("message", message);
        }
        else if(author.equals("")) {
            String message = "Please Sign in";
            request.setAttribute("message", message);
        }
        else if (SGList.isEmpty()){
            String message = "Invalid Subgreennit Name";
            request.setAttribute("message", message);
        }else{
            int host = SGList.get(0).getId();
            threadManager.createThread(host, title, author, content, new Timestamp(System.currentTimeMillis()));
            String message = "Thread Posted!";
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        threadManager.exit();
        subgreennitManager.exit();
    }
}

