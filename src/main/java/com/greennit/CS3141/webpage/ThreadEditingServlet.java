package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Thread;
import com.greennit.CS3141.managers.PostManager;
import com.greennit.CS3141.managers.ThreadManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit_threads")
public class ThreadEditingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ThreadEditingServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Managers required for deleting the post
        ThreadManager threadManager = new ThreadManager();

        String id = request.getParameter("id"); //Gets the thread id for redirection
        String newContent = request.getParameter("new_content");
        String message; //Prints a message back to the user
        String destPage = "index.jsp"; //Used to redirect the user

        if(id.equals("")){ //Checks if the thread has an id
            message = "Invalid Thread ID";
        }else{
            try{
                Thread thread = threadManager.getThread(Integer.parseInt(id)); //Gets the thread
                destPage = "thread?id=" + thread.getId(); //Assigns thread to the destPage
                threadManager.updateThreadContent(Integer.parseInt(id), newContent); //edits the post
                message = "";
            }catch (IllegalArgumentException ex){
                message = "Edit Failed";
            }
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        threadManager.exit();
    }


}
