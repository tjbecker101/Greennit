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

@WebServlet("/deletePosts")
public class PostDeletionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public PostDeletionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Managers required for deleting the post
        PostManager postManager = new PostManager();
        ThreadManager threadManager = new ThreadManager();

        String id = request.getParameter("post_id"); //Gets the post id
        String threadId = request.getParameter("thread_id"); //Gets the thread id for redirection
        String message = "Error"; //Prints a message back to the user
        String destPage = "index.jsp"; //Used to redirect the user

        if(id.equals("")){ //Checks if the post has an id
            message = "Invalid Post ID";
        }else if(threadId.equals("")) { //Checks if the post is in a thread
            message = "Invalid Thread ID";
        }else{
            try{
                Thread thread = threadManager.getThread(Integer.parseInt(threadId)); //Gets the thread the post is in
                destPage = "thread?id=" + thread.getId(); //Assigns thread to the destPage
                postManager.deletePost(Integer.parseInt(id)); //Deletes the post
                message = "Deletion Successful";
            }catch (IllegalArgumentException ex){
                message = "Deletion Failed";
            }
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        postManager.exit();
        threadManager.exit();
    }


}
